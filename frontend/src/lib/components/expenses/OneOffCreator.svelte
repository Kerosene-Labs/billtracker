<script lang="ts">
  import { ExpensesApi, ResponseError } from "$lib/sdk";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import { goto } from "$app/navigation";
  import {
    addToToastQueue,
    EButton,
    ECard,
    EDateInput,
    ENumberInput,
    ETextInput,
    ToastType,
  } from "@kerosenelabs/eureka";

  let amount: number = 0.0;
  let description: string;
  let date: Date = "";

  function createOneOff() {
    new ExpensesApi(getPrivateApiConfig())
      .createOneOff({
        createOneOffExpenseRequest: {
          amount,
          date: new Date(date),
          description: description,
        },
      })
      .then((response) => {
        addToToastQueue({
          message: "Successfully created one-off expense.",
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
  title="Create One-Off"
  subtitle="For example, a one-off expense might be getting coffee with a friend.">
  <div class="flex flex-col gap-4">
    <div class="flex h-fit w-full flex-col gap-4 xl:flex-row">
      <ENumberInput id="amount" label="Dollars" prefix="$" bind:value={amount}
      ></ENumberInput>
      <ETextInput id="description" label="Description" bind:value={description}
      ></ETextInput>
      <EDateInput id="date" label="Date" bind:value={date}></EDateInput>
    </div>
    <EButton onclick={createOneOff}>Create</EButton>
  </div>
</ECard>
