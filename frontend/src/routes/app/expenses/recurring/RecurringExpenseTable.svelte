<script lang="ts">
  import { onMount } from "svelte";
  import { getPrivateApiConfig } from "$lib/sdkUtil";
  import {
    ExpensesApi,
    type RecurringExpenseEventCreator,
    ResponseError,
  } from "$lib/sdk";
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

  let recurringExpenseEventCreators:
    | RecurringExpenseEventCreator[]
    | undefined = undefined;

  onMount(() => {
    new ExpensesApi(getPrivateApiConfig())
      .getRecurringExpenseCreators()
      .then((response) => {
        recurringExpenseEventCreators = response.recurringExpenseEventCreators;
      })
      .catch(async (error: ResponseError) => {
        addToToastQueue({
          message: "Failed to get recurring expenses.",
          type: ToastType.ERROR,
        });
      });
  });
</script>

<div class="flex flex-col gap-4">
  <div class="flex flex-col gap-2 text-nowrap xl:ml-auto xl:flex-row">
    <EButton
      onclick={() => {
        goto("/app/expenses/recurring/create");
      }}
      >Create Recurring
    </EButton>
  </div>
  {#if recurringExpenseEventCreators === undefined}
    <!--Loading Spinner-->
    <div class="flex w-full justify-center p-6">
      <ESpinner></ESpinner>
    </div>
  {:else if recurringExpenseEventCreators !== undefined && recurringExpenseEventCreators.length === 0}
    <div class="flex flex-col items-center justify-center gap-2 p-8">
      <EP>🦗...Just crickets</EP>
      <EP>There's nothing here.</EP>
    </div>
  {:else}
    <ETable headers={["Amount", "Interval (every month)", "Description"]}>
      {#each recurringExpenseEventCreators as row}
        <ETableRow
          onclick={() => {
            goto("/app/expenses/recurring/editor?id=" + row.id);
          }}
          row={[
            row.amount.toString(),
            row.recursEveryCalendarDay.toString(),
            row.description,
          ]}></ETableRow>
      {/each}
    </ETable>
  {/if}
</div>
