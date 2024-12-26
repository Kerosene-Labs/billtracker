<script lang="ts">
    import Card from "$lib/tk/Card.svelte";
    import LineEdit from "$lib/tk/LineEdit.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {ExpensesApi, ResponseError} from "$lib/sdk";
    import {getErrorMessageFromSdk, getPrivateApiConfig} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import {goto} from "$app/navigation";

    let amount: number = 0.00;
    let date: string = "";

    function createOneOff() {
        new ExpensesApi(getPrivateApiConfig()).createOneOff({createOneOffExpenseRequest: {amount, date: new Date(date)}})
            .then((response) => {
                    addToToastQueue({message: "Successfully created one-off expense.", type: ToastType.SUCCESS})
                    goto("/app/expenses")
                }
            ).catch(async (error: ResponseError) => {
            await getErrorMessageFromSdk(error)
                .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
        })
    }
</script>

<Card title="Create One-Off"
      subtitle="For example, a one-off expense might be getting coffee with a friend.">
    <div class="flex flex-col gap-4">
        <div class="flex flex-col xl:flex-row gap-4 h-fit w-full">
            <LineEdit id="amount" type="number" label="Amount" bind:value={amount}></LineEdit>
            <LineEdit id="date" type="date" label="Date" bind:value={date}></LineEdit>
        </div>
        <Button on:click={createOneOff}>Create</Button>
    </div>
</Card>