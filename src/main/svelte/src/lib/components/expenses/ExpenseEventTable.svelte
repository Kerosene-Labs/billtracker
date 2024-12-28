<script lang="ts">
  import { onMount } from "svelte";
  import { getPrivateApiConfig } from "$lib/sdkUtil";
  import { type ExpenseEvent, ExpensesApi, ResponseError } from "$lib/sdk";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import Spinner from "$lib/tk/Spinner.svelte";
  import Button from "$lib/tk/Button.svelte";
  import Table from "$lib/components/Table.svelte";
  import { goto } from "$app/navigation";

  let expenses: ExpenseEvent[] | undefined = undefined;
  let expenseRows: String[][] = [];

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
          message: "Failed to get Expenses.",
          type: ToastType.ERROR,
        });
      });
  });
</script>

<div class="flex flex-col gap-4">
  <div class="flex flex-col gap-2 text-nowrap xl:ml-auto xl:flex-row">
    <Button
      on:click={() => {
        goto("/app/expenses/createOneOff");
      }}
      >Create One-off
    </Button>
    <Button disabled={true}>Export CSV</Button>
    <Button disabled={true}>Export JSON</Button>
  </div>
  {#if expenses === undefined}
    <!--Loading Spinner-->
    <div class="flex w-full justify-center p-6">
      <Spinner></Spinner>
    </div>
  {:else if expenses !== undefined && expenses.length === 0}
    <div class="flex flex-col items-center justify-center gap-2 p-8">
      <p class="font-mono text-2xl font-black text-neutral-300">
        🦗...crickets
      </p>
      <p class="font-semibold text-neutral-100">There's nothing here.</p>
    </div>
  {:else}
    <Table
      headers={["Amount", "Occurred On", "Description", "Type"]}
      rows={expenseRows}
    ></Table>
  {/if}
</div>
