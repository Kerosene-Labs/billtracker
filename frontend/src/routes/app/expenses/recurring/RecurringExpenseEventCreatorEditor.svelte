<script lang="ts">
  import {
    ExpensesApi,
    type RecurringExpenseEventCreator,
    ResponseError
  } from "$lib/sdk";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import { onMount } from "svelte";
  import Card from "$lib/tk/Card.svelte";
  import Button from "$lib/tk/Button.svelte";
  import Table from "$lib/eureka/table/Table.svelte";
  import TableRow from "$lib/eureka/table/TableRow.svelte";
  import Spinner from "$lib/tk/Spinner.svelte";
  import { getOrdinal } from "$lib/sdkUtil";
  import Modal from "$lib/tk/Modal.svelte";
  import ENumberInput from "$lib/eureka/input/ENumberInput.svelte";

  export let id: string;
  let data: RecurringExpenseEventCreator | undefined = undefined;
  let supersedeModalVisible: boolean = false;

  onMount(() => {
    new ExpensesApi(getPrivateApiConfig())
      .getRecurringExpenseCreators({ ids: [id] })
      .then((response) => {
        data = response.recurringExpenseEventCreators[0];
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR })
        );
      });
  });
</script>

<Modal title="Supersede" subtitle="In a nutshell, this will create a new iteration of this Recurring Expense. You can pick which details carry over, which have changed, and we'll seamlessly handle the transition." bind:visible={supersedeModalVisible}>
  <ENumberInput></ENumberInput>
</Modal>

<Card title="Details" subtitle="In-depth details of this Recurring Expense.">
  {#if data}
    <div class="flex flex-col gap-2">
      <Table headers={["Key", "Value"]}>
        <TableRow row={["Amount", "$" + data.amount]}></TableRow>
        <TableRow row={["Description", data.description]}></TableRow>
        <TableRow row={["Recurs every (Calendar Day)", getOrdinal(data.recursEveryCalendarDay)]}></TableRow>
      </Table>
    </div>
  {:else}
    <Spinner></Spinner>
  {/if}
</Card>
<Card title="Actions" subtitle="Actions you can perform that change the state of this Recurring Expense.">
  <div class="flex flex-col xl:flex-row gap-2">
    <Button on:click={() => {supersedeModalVisible = true}} title="Supersede this recurring expense">
      Supersede
    </Button>
    <Button
      disabled={true}
      title="Delete this recurring expense. It will no longer post automatically, but existing posted expenses will remain.">
      Delete
    </Button>
  </div>
</Card>
