<script lang="ts">
  import { ExpensesApi, ResponseError } from "$lib/sdk";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import { goto } from "$app/navigation";
  import {
    addToToastQueue,
    EButton,
    ECard,
    ENumberInput,
    ETextInput,
    ToastType,
  } from "@kerosenelabs/eureka";

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

<ECard
  title="Create Recurring Expense"
  subtitle="These are expenses that'll automatically appear under your Posted Expenses, typically for expenses that recur on the same date every month.">
  <div class="flex flex-col gap-4">
    <div class="flex h-fit w-full flex-col gap-4 xl:flex-row">
      <ENumberInput id="amount" label="Amount" prefix="$" bind:value={amount}
      ></ENumberInput>
      <ETextInput id="description" label="Description" bind:value={description}
      ></ETextInput>
      <ENumberInput
        id="calendarDay"
        label="Calendar Day"
        bind:value={calendarDay}
        min={1}
        max={28}></ENumberInput>
    </div>
    <EButton onclick={createRecurringExpenseCreator}>Create</EButton>
  </div>
</ECard>
