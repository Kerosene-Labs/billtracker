<script>
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";
</script>

<div class="flex flex-col items-center p-8 min-h-full gap-2">
    <Card title="Home" subtitle="This thing's still in the oven, so please mind the quirks.">
        <div class="flex flex-col gap-2 lg:flex-row text-nowrap">
            <Button>Report a Bug</Button>
            <Button>Settings</Button>
            <Button>Log Out</Button>
        </div>
    </Card>
</div>