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

<div class="rounded-2xl bg-neutral-800 flex flex-col gap-2 text-center">
    <!-- Header -->
    <div class="grid grid-cols-4 font-mono font-bold text-sm rounded-t-2xl bg-neutral-900 p-4">
        <span>Amount</span>
        <span>Date</span>
        <span>Description</span>
        <span>Type</span>
    </div>
    {#if expenses === undefined}
        <!--Loading Spinner-->
        <div class="flex w-full">
            <Spinner></Spinner>
        </div>
    {:else}
        <!--Content-->
        <div class="flex flex-col gap-2 p-2">
            {#each expenses as expense}
                <div class="grid grid-cols-4">
                    <span>${expense.amount}</span>
                    <span>{expense.date.toDateString()}</span>
                    <span>...</span>
                    <span>...</span>
                </div>
            {/each}
        </div>
    {/if}
</div>