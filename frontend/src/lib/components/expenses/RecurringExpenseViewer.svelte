<script lang="ts">
  import type { RecurringExpenseEventCreator } from "$lib/sdk";
  import Card from "$lib/tk/Card.svelte";
  import { getOrdinal } from "$lib/sdkUtil";
  import Table from "$lib/eureka/table/Table.svelte";
  import TableRow from "$lib/eureka/table/TableRow.svelte";
  import Button from "$lib/tk/Button.svelte";
  import Modal from "$lib/tk/Modal.svelte";

  interface Props {
    recurringExpense: RecurringExpenseEventCreator;
    actionsVisible: boolean;
  }

  let { recurringExpense, actionsVisible = true }: Props = $props();

  let supersedeModalVisible: boolean = $state(false);
</script>

<Card title="Details" subtitle="In-depth details of this Recurring Expense.">
  <div class="flex flex-col gap-2">
    <Table headers={["Key", "Value"]}>
      <TableRow row={["Amount", "$" + recurringExpense.amount]}></TableRow>
      <TableRow row={["Description", recurringExpense.description]}></TableRow>
      <TableRow row={["Recurs every (Calendar Day)", getOrdinal(recurringExpense.recursEveryCalendarDay)]}></TableRow>
    </Table>
  </div>
</Card>
{#if actionsVisible}
  <Modal visible={supersedeModalVisible}></Modal>
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
{/if}
