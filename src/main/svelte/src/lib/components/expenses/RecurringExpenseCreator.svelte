<script lang="ts">
  import Card from "$lib/tk/Card.svelte";
  import LineEdit from "$lib/tk/LineEdit.svelte";
  import Button from "$lib/tk/Button.svelte";
  import { ExpensesApi, ResponseError } from "$lib/sdk";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import { goto } from "$app/navigation";

  let amount: number;
  let description: string;
  let calendarDay: number;

  function createRecurringExpenseCreator() {
    new ExpensesApi(getPrivateApiConfig())
      .createRecurringExpenseCreator({
        createRecurringExpenseCreatorRequest: {
          amount: amount,
          description: description,
          recursEveryCalendarDay: calendarDay
        },
      })
      .then((response) => {
        addToToastQueue({
          message: "Successfully created recurring expense.",
          type: ToastType.SUCCESS,
        });
        goto("/app/expenses");
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
  }
</script>

<Card
  title="Create Recurring Expense"
  subtitle="These are expenses that'll automatically appear under your Posted Expenses, typically for expenses that recur on the same date every month."
>
  <div class="flex flex-col gap-4">
    <div class="flex h-fit w-full flex-col gap-4 xl:flex-row">
      <LineEdit id="amount" label="Dollars" type="number" bind:value={amount}
      ></LineEdit>
      <LineEdit
        id="description"
        type="text"
        label="Description"
        bind:value={description}
      ></LineEdit>
      <LineEdit id="calendarDay" label="Calendar Day" type="number" bind:value={calendarDay}
                min={1} max={28} title="Must be between 1 and 28, reprenseting all days possible within the shortest month."
      ></LineEdit>
    </div>
    <Button on:click={createRecurringExpenseCreator}>Create</Button>
  </div>
</Card>
