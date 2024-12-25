<script>
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {goto} from "$app/navigation";
</script>

<Card title="Expenses" subtitle="Things that consume your money.">
    <div class="flex ml-auto text-nowrap">
        <Button on:click={() => {goto("/app/expenses/add")}}>Add Expense</Button>
    </div>
</Card>