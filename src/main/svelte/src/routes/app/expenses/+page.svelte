<script>
  import Card from "$lib/tk/Card.svelte";
  import Button from "$lib/tk/Button.svelte";
  import { goto } from "$app/navigation";
  import ExpenseEventTable from "$lib/components/expenses/ExpenseEventTable.svelte";
</script>

<Card
  title="Introduction"
  subtitle="For example, an expense could be your mortgage payment, a power bill, or dinner with a friend."
>
  <div class="flex flex-col xl:flex-row gap-2">
    <Button disabled={true}>Create Calendar-based</Button>
    <Button disabled={true}>Create Time-based</Button>
    <Button
      on:click={() => {
        goto("/app/expenses/createOneOff");
      }}>Create One-off</Button
    >
  </div>
</Card>
<Card title="Your Expenses">
  <ExpenseEventTable></ExpenseEventTable>
</Card>
