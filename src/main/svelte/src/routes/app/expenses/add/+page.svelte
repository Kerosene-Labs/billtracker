<script lang="ts">
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";
    import LineEdit from "$lib/tk/LineEdit.svelte";
    import OneOffCreator from "$lib/components/expenses/OneOffCreator.svelte";

    // which step are we on for creation?
    let type: string | undefined = undefined;
</script>

{#if !type}
    <Card title="Expense Type" subtitle="What kind of expense is this?">
        <div class="flex flex-col gap-2 xl:flex-row text-nowrap">
            <Button disabled={true}>Time based</Button>
            <Button disabled={true}>Calendar based</Button>
            <Button on:click={() => {type = "one-off"}}>One-off</Button>
        </div>
    </Card>
{:else if type === "one-off"}
    <OneOffCreator></OneOffCreator>
{/if}