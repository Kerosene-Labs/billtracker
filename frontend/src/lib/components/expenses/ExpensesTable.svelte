<script lang="ts">
  import { onMount } from "svelte";
  import { getPrivateApiConfig } from "$lib/sdkUtil";
  import { type ExpenseEvent, ExpensesApi, ResponseError } from "$lib/sdk";
  import { goto } from "$app/navigation";
  import {
    addToToastQueue,
    EButton,
    EP,
    ESpinner,
    ETable,
    ETableRow,
    ToastType,
  } from "@kerosenelabs/eureka";

  let expenses: ExpenseEvent[] | undefined = undefined;
  let expenseRows: string[][] = [];

  onMount(() => {
    new ExpensesApi(getPrivateApiConfig())
      .getExpenses()
      .then((response) => {
        expenses = response.expenseEvents;
        expenses.forEach((expense) => {
          expenseRows.push([
            "$" + expense.amount.toFixed(2),
            expense.date.toDateString(),
            expense.description,
            expense.expenseEventType,
          ]);
        });
      })
      .catch(async (error: ResponseError) => {
        addToToastQueue({
          message: "Failed to get expenses.",
          type: ToastType.ERROR,
        });
      });
  });
</script>

<div class="flex flex-col gap-4">
  <div class="flex flex-col gap-2 text-nowrap xl:ml-auto xl:flex-row">
    <EButton
      onclick={() => {
        goto("/app/expenses/createOneOff");
      }}
      >Create One-off
    </EButton>
    <EButton disabled={true}>Export CSV</EButton>
    <EButton disabled={true}>Export JSON</EButton>
  </div>
  {#if expenses === undefined}
    <!--Loading Spinner-->
    <div class="flex w-full justify-center p-6">
      <ESpinner></ESpinner>
    </div>
  {:else if expenses !== undefined && expenses.length === 0}
    <div class="flex flex-col items-center justify-center gap-2 p-8">
      <EP>🦗...Just crickets</EP>
      <EP>There's nothing here.</EP>
    </div>
  {:else}
    <ETable headers={["Amount", "Occurred On", "Description", "Type"]}>
      {#each expenseRows as row}
        <ETableRow {row}></ETableRow>
      {/each}
    </ETable>
  {/if}
</div>
