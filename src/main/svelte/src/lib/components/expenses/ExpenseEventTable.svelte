<script lang="ts">

    import {onMount} from "svelte";
    import {getPrivateApiConfig} from "$lib/sdkUtil";
    import {type ExpenseEvent, ExpensesApi, ResponseError} from "$lib/sdk";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import Spinner from "$lib/tk/Spinner.svelte";

    let expenses: ExpenseEvent[] | undefined = undefined;

    onMount(() => {
        new ExpensesApi(getPrivateApiConfig()).getExpenses()
            .then((response) => {
                    expenses = response.expenseEvents;
                }
            ).catch(async (error: ResponseError) => {
            addToToastQueue({
                message: "Failed to get Expenses.", type: ToastType.ERROR
            })
        })
    })
</script>

<div class="rounded-2xl bg-neutral-800 flex flex-col text-center overflow-clip max-h-[20rem]">
    {#if expenses === undefined}
        <!--Loading Spinner-->
        <div class="flex w-full justify-center p-6">
            <Spinner></Spinner>
        </div>
    {:else if expenses !== undefined && expenses.length == 0}
        <div class="flex p-8 items-center justify-center flex-col gap-2">
            <p class="font-mono font-black text-2xl text-neutral-300">🦗...crickets</p>
            <p class="text-neutral-100 font-semibold">There's no expenses here.</p>
        </div>
    {:else}
        <!-- Header -->
        <div class="grid grid-cols-4 font-mono font-bold text-sm rounded-t-2xl bg-neutral-900/80 p-4 text-neutral-400">
            <span>Amount</span>
            <span>Occurred On</span>
            <span>Description</span>
            <span>Type</span>
        </div>
        <!--Content-->
        <div class="flex flex-col font-semibold text-white overflow-y-auto">
            {#each expenses as expense}
                <div class="grid grid-cols-4 items-center hover:bg-neutral-700/10 active:bg-neutral-700/20 p-4 transition-colors border-b border-b-neutral-700/50">
                    <span>${expense.amount}</span>
                    <span>{expense.date.toDateString()}</span>
                    <span>...</span>
                    <span class="bg-neutral-900/50 p-2 rounded-xl font-mono text-neutral-300 text-sm">One-off</span>
                </div>
            {/each}
        </div>
    {/if}
</div>