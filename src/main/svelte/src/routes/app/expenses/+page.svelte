<script>
  import Card from "$lib/tk/Card.svelte";
  import Button from "$lib/tk/Button.svelte";
  import { goto } from "$app/navigation";
  import ExpenseEventTable from "$lib/components/expenses/ExpenseEventTable.svelte";
  import Table from "$lib/components/Table.svelte";
</script>

<Card
  title="Introduction"
  subtitle="For example, an expense could be your mortgage payment, a power bill, or dinner with a friend."
>
  <div class="flex flex-col xl:flex-row gap-2">
    <Button on:click={() => {
      goto("/app/expenses/createFixed")
    }}>Create Fixed
    </Button>
    <Button
      on:click={() => {
        goto("/app/expenses/createOneOff");
      }}>Create One-off
    </Button
    >
  </div>
</Card>
<Card title="Posted Expenses">
  <ExpenseEventTable></ExpenseEventTable>
</Card>
<Card title="Recurring Expenses"
      subtitle="Expenses that occur on a fixed schedule, like your mortgage. When these post, you'll see them under your Posted Expenses.">
  <Table headers={[""]}></Table>
</Card>