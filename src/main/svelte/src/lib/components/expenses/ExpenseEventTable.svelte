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
    <!-- Header -->
    <div class="grid grid-cols-4 font-mono font-bold text-sm rounded-t-2xl bg-neutral-900/80 p-4 text-neutral-400">
        <span>Amount</span>
        <span>Occurred On</span>
        <span>Description</span>
        <span>Type</span>
    </div>
    {#if expenses === undefined}
        <!--Loading Spinner-->
        <div class="flex w-full justify-center p-12 scale-[180%]">
            <Spinner></Spinner>
        </div>
    {:else}
        <!--Content-->
        <div class="flex flex-col font-semibold text-white overflow-y-auto">
            {#each expenses as expense}
                <div class="grid grid-cols-4 hover:bg-neutral-700/10 active:bg-neutral-700/20 p-4 transition-colors border-b border-b-neutral-700/50">
                    <span>${expense.amount}</span>
                    <span>{expense.date.toDateString()}</span>
                    <span>...</span>
                    <span>...</span>
                </div>
            {/each}
        </div>
    {/if}
</div>