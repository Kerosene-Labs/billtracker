<script lang="ts">
    import Card from "$lib/tk/Card.svelte";
    import {onMount} from "svelte";
    import {AuthControllerApi} from "$lib/sdk";
    import Button from "$lib/tk/Button.svelte";
    import {goto} from "$app/navigation";
    import ErrorCard from "$lib/tk/ErrorCard.svelte";

    let confirmed: boolean = false;
    let error: string | undefined = undefined;

    onMount(() => {
        new AuthControllerApi().confirmuser({token: new URL(window.location.href).searchParams.get("token") as string})
            .then((result) => {
                confirmed = true
            })
            .catch((err) => {
                error = err
            })
            .finally(() => {
            });
    })

</script>

<div class="flex flex-col items-center justify-center min-h-full">
    {#if confirmed}
        <Card title="Confirmed" subtitle="You're all confirmed!">
            <Button on:click={() => {goto('/')}}>Log In</Button>
        </Card>
    {:else if !confirmed && !error}
            <Card title="Confirming" subtitle="Communicating with the mother-ship...">
            </Card>
    {/if}
    {#if error}
        <ErrorCard>{error}</ErrorCard>
    {/if}
</div>
