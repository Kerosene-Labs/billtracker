<script lang="ts">
  import Card from "$lib/tk/Card.svelte";
  import LineEdit from "$lib/tk/LineEdit.svelte";
  import Button from "$lib/tk/Button.svelte";
  import { ExpensesApi, ResponseError } from "$lib/sdk";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import { goto } from "$app/navigation";
  import DollarEdit from "$lib/eureka/EurekaInput.svelte";
  import EurekaInput from "$lib/eureka/EurekaInput.svelte";

  let amount: number = 0.0;
  let description: string;
  let date: string = "";

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

<Card
  title="Create One-Off"
  subtitle="For example, a one-off expense might be getting coffee with a friend."
>
  <div class="flex flex-col gap-4">
    <div class="flex h-fit w-full flex-col gap-4 xl:flex-row">
      <EurekaInput
        id="amount"
        label="Amount"
        bind:value={amount}
        type="number"
        suffix="$"
      ></EurekaInput>
      <LineEdit
        id="description"
        type="text"
        label="Description"
        bind:value={description}
      ></LineEdit>
      <LineEdit id="date" type="date" label="Date" bind:value={date}></LineEdit>
    </div>
    <Button on:click={createOneOff}>Create</Button>
  </div>
</Card>
