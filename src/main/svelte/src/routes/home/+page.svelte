<script lang="ts">
    import Card from "$lib/tk/Card.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {AuthApi} from "$lib/sdk";
    import {apiConfig, validateAndEnforceSession} from "$lib/sdkUtil";
    import {goto} from "$app/navigation";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import {onMount} from "svelte";
    import IntroductionsEditor from "$lib/components/IntroductionsCard.svelte";

    onMount(async () => {
        await validateAndEnforceSession();
    })

    async function doLogOut() {
        await new AuthApi(apiConfig).logout();
        addToToastQueue({message: "Logged out", type: ToastType.INFO})
        goto("/");
    }
</script>


<Card title="Home" subtitle="This thing's still in the oven, so please mind the quirks.">
    <div class="flex flex-col gap-2 lg:flex-row text-nowrap">
        <Button on:click={() => {window.location.href = "https://github.com/Kerosene-Labs/billtracker/issues/new"}}>
            Report a Bug
        </Button>
        <Button on:click={() => {goto("/settings")}}>Settings</Button>
        <Button on:click={doLogOut}>Log Out</Button>
    </div>
</Card>
<IntroductionsEditor hideIfPopulated={true}></IntroductionsEditor>
<Card title="Expenses" subtitle="Where's your money going?"></Card>
<Card title="Income" subtitle="How much is coming in?"></Card>
