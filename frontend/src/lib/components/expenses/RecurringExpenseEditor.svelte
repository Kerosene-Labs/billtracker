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
  import Table from "$lib/eureka/table/ETable.svelte";
  import TableRow from "$lib/eureka/table/ETableRow.svelte";
  import Spinner from "$lib/tk/Spinner.svelte";
  import { getOrdinal } from "$lib/sdkUtil";
  import Modal from "$lib/tk/Modal.svelte";
  import ENumberInput from "$lib/eureka/input/ENumberInput.svelte";
  import EButton from "$lib/eureka/button/EButton.svelte";
  import ETextInput from "$lib/eureka/input/ETextInput.svelte";
  import EDateInput from "$lib/eureka/input/EDateInput.svelte";
  import EHorizontalSeparator from "$lib/eureka/layout/EHorizontalSeparator.svelte";

  // props
  export let id: string;

  // state
  let data: RecurringExpenseEventCreator | undefined = undefined;

  // supersede modal
  let supersedeModalVisible: boolean = false;
  let supersedeAmount: number;
  let supersedeDescription: string;
  let supersedeRecursEveryCalendarDay: number;

  // delete modal
  let deleteModalVisible: boolean = false;
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

<Modal title="Supersede"
       subtitle="This will create a new iteration of this Recurring Expense. Enter the new details, and we'll handle the transition."
       closeButtonVisible={false}
       bind:visible={supersedeModalVisible}>
  <div class="flex flex-col gap-2">
    <ENumberInput id="amount" label="Amount" prefix="$" bind:value={supersedeAmount}></ENumberInput>
    <ETextInput id="description" label="Description" bind:value={supersedeDescription}></ETextInput>
    <ENumberInput id="calendarDay" label="Recurs every (Calendar Day)" bind:value={supersedeRecursEveryCalendarDay}></ENumberInput>
    <div class="flex flex-col desktop:flex-row gap-2">
      <EButton type="secondary" onclick={() => {supersedeModalVisible = false}}>Cancel</EButton>
      <EButton>Continue</EButton>
    </div>
  </div>
</Modal>

<Modal title="Delete this?"
       subtitle="Are you sure? You'll be permanently deleting this recurring expense. Any posted expenses will remain."
        bind:visible={deleteModalVisible}
       closeButtonVisible={false}
>
  <div class="flex flex-col desktop:flex-row gap-2">
    <EButton type="secondary" onclick={() => {deleteModalVisible = false}}>Cancel</EButton>
    <EButton type="danger">Yes, delete this</EButton>
  </div>
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
    <EButton onclick={() => {supersedeModalVisible = true}} title="Supersede this recurring expense">
      Supersede
    </EButton>
    <EButton
      onclick={() => {deleteModalVisible = true}}
      type="danger"
      title="Delete this recurring expense. It will no longer post automatically, but existing posted expenses will remain.">
      Delete
    </EButton>
  </div>
</Card>
