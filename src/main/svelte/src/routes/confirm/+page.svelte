<script lang="ts">
    import Card from "$lib/tk/Card.svelte";
    import {onMount} from "svelte";
    import {AuthApi} from "$lib/sdk";
    import Button from "$lib/tk/Button.svelte";
    import {goto} from "$app/navigation";
    import {apiConfig, getErrorMessageFromSdk} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";

    let confirmed: boolean = false;

    onMount(() => {
        new AuthApi(apiConfig).confirmuser({token: new URL(window.location.href).searchParams.get("token") as string})
            .then((result) => {
                confirmed = true
                addToToastQueue({message: "You're confirmed!", type: ToastType.SUCCESS})
            })
            .catch(async (error) => {
                await getErrorMessageFromSdk(error)
                    .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
            })
    })

</script>

{#if confirmed}
    <Card title="Confirmed" subtitle="You're all confirmed!">
        <Button on:click={() => {goto('/')}}>Log In</Button>
    </Card>
{:else if !confirmed}
        <Card title="Confirming" subtitle="Communicating with the mother-ship...">
            <Button spinning={true} on:click={() => {goto('/')}}>Waiting...</Button>
        </Card>
{/if}
