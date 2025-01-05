<script lang="ts">
  import Card from "$lib/tk/Card.svelte";
  import LineEdit from "$lib/tk/LineEdit.svelte";
  import Button from "$lib/tk/Button.svelte";
  import { ExpensesApi, ResponseError } from "$lib/sdk";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import { goto } from "$app/navigation";
  import ENumberInput from "$lib/eureka/input/ENumberInput.svelte";
  import ETextInput from "$lib/eureka/input/ETextInput.svelte";
  import EDateInput from "$lib/eureka/input/EDateInput.svelte";

  let amount: number;
  let description: string;
  let calendarDay: number;

  function createRecurringExpenseCreator() {
    new ExpensesApi(getPrivateApiConfig())
      .createRecurringExpenseCreator({
        createRecurringExpenseCreatorRequest: {
          amount: amount,
          description: description,
          recursEveryCalendarDay: calendarDay,
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
      <ENumberInput id="amount" label="Amount" prefix="$" bind:value={amount}
      ></ENumberInput>
      <ETextInput
        id="description"
        label="Description"
        bind:value={description}
      ></ETextInput>
      <ENumberInput
        id="calendarDay"
        label="Calendar Day"
        bind:value={calendarDay}
        min={1}
        max={28}
      ></ENumberInput>
    </div>
    <Button on:click={createRecurringExpenseCreator}>Create</Button>
  </div>
</Card>
