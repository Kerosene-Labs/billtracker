<script lang="ts">
  import { onMount } from "svelte";
  import { getPrivateApiConfig } from "$lib/sdkUtil";
  import { type ExpenseEvent, ExpensesApi, ResponseError } from "$lib/sdk";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import Spinner from "$lib/tk/Spinner.svelte";
  import Button from "$lib/tk/Button.svelte";

  let expenses: ExpenseEvent[] | undefined = undefined;

  onMount(() => {
    new ExpensesApi(getPrivateApiConfig())
      .getExpenses()
      .then((response) => {
        expenses = response.expenseEvents;
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
    <Button disabled={true}>Export CSV</Button>
    <Button disabled={true}>Export JSON</Button>
  </div>
  <div
    class="flex max-h-[20rem] flex-col overflow-clip overflow-y-auto rounded-2xl bg-neutral-800 text-center"
  >
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
        <p class="font-semibold text-neutral-100">There's no expenses here.</p>
      </div>
    {:else}
      <!-- Header -->
      <div class="overflow-y-scroll">
        <div class="min-w-[30rem]">
          <div
            class="grid grid-cols-4 text-nowrap rounded-t-2xl bg-neutral-900/80 p-4 font-mono text-sm font-bold text-neutral-400"
          >
            <span>Amount</span>
            <span>Occurred On</span>
            <span>Description</span>
            <span>Type</span>
          </div>
          <!--Content-->
          <div class="flex flex-col overflow-y-auto font-semibold text-white">
            {#each expenses as expense}
              <div
                class="grid grid-cols-4 items-center border-b border-b-neutral-700/50 p-4 transition-colors hover:bg-neutral-700/10 active:bg-neutral-700/20"
              >
                <span>${expense.amount}</span>
                <span>{expense.date.toDateString()}</span>
                <span>{expense.description}</span>
                <span
                  class="rounded-xl bg-neutral-900/50 p-2 font-mono text-sm text-neutral-300"
                  >One-off</span
                >
              </div>
            {/each}
          </div>
        </div>
      </div>
    {/if}
  </div>
</div>
