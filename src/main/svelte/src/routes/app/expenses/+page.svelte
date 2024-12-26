<script>
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {goto} from "$app/navigation";
    import ExpenseEventTable from "$lib/components/expenses/ExpenseEventTable.svelte";
</script>

<Card title="Expenses"
      subtitle="For example, an expense could be your mortage payment, a power bill, or dinner with a friend.">
    <div class="flex flex-col gap-4">
        <div class="flex ml-auto text-nowrap">
            <Button on:click={() => {goto("/app/expenses/add")}}>Add Expense</Button>
        </div>
    </div>
</Card>
<Card title="Your Expenses">
    <ExpenseEventTable></ExpenseEventTable>
</Card>