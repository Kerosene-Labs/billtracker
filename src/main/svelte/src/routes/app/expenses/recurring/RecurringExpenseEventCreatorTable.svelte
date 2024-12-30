<script lang="ts">
  import { onMount } from "svelte";
  import { getPrivateApiConfig } from "$lib/sdkUtil";
  import {
    ExpensesApi,
    type RecurringExpenseEventCreator,
    ResponseError,
  } from "$lib/sdk";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import Spinner from "$lib/tk/Spinner.svelte";
  import Button from "$lib/tk/Button.svelte";
  import Table from "$lib/eureka/table/Table.svelte";
  import { goto } from "$app/navigation";
  import TableRow from "$lib/eureka/table/TableRow.svelte";

  let recurringExpenseEventCreators:
    | RecurringExpenseEventCreator[]
    | undefined = undefined;

  function getOrdinal(num: number) {
    const suffixes = ["th", "st", "nd", "rd"];
    const value = num % 100;
    return (
      num + (suffixes[(value - 20) % 10] || suffixes[value] || suffixes[0])
    );
  }

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
    <Button
      on:click={() => {
        goto("/app/expenses/recurring/create");
      }}
      >Create Recurring
    </Button>
  </div>
  {#if recurringExpenseEventCreators === undefined}
    <!--Loading Spinner-->
    <div class="flex w-full justify-center p-6">
      <Spinner></Spinner>
    </div>
  {:else if recurringExpenseEventCreators !== undefined && recurringExpenseEventCreators.length === 0}
    <div class="flex flex-col items-center justify-center gap-2 p-8">
      <p class="font-mono text-2xl font-black text-neutral-300">
        🦗...crickets
      </p>
      <p class="font-semibold text-neutral-100">There's nothing here.</p>
    </div>
  {:else}
    <Table headers={["Amount", "Interval (every month)", "Description"]}>
      {#each recurringExpenseEventCreators as row}
        <TableRow
          on:click={() => {
            goto("/app/expenses/recurring/" + row.id);
          }}
          row={[
            row.amount.toString(),
            row.recursEveryCalendarDay.toString(),
            row.description,
          ]}
        ></TableRow>
      {/each}
    </Table>
  {/if}
</div>
