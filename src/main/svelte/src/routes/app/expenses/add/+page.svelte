<script lang="ts">
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";

    let step = 0;
    let type: string | undefined = undefined;
</script>

{#if step === 0}
    <Card title="Expense Type" subtitle="What kind of expense is this?">
        <div class="flex flex-col gap-2 xl:flex-row text-nowrap">
            <Button disabled={true}>Time based</Button>
            <Button disabled={true}>Calendar based</Button>
            <Button on:click={() => {type = "onetime"; step = 1}}>One Time</Button>
        </div>
    </Card>
{:else if step === 1}
    <Card title="Expense Details" subtitle="">
<!--        <LineEdit id=""></LineEdit>-->
    </Card>
{/if}