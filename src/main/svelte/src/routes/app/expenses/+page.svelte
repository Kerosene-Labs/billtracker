<script>
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {goto} from "$app/navigation";
    import ExpenseEventTable from "$lib/components/expenses/ExpenseEventTable.svelte";
</script>

<Card title="Expenses" subtitle="Things that consume your money.">
    <div class="flex flex-col gap-4">
        <div class="flex ml-auto text-nowrap">
            <Button on:click={() => {goto("/app/expenses/add")}}>Add Expense</Button>
        </div>
        <ExpenseEventTable></ExpenseEventTable>
    </div>
</Card>