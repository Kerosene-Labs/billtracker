<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import {doLogOut, getErrorMessageFromSdk, getPrivateApiConfig} from "$lib/sdkUtil";
    import {onMount} from "svelte";
    import {ResponseError, SettingsApi} from "$lib/sdk";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import Spinner from "$lib/tk/Spinner.svelte";

    let loading: boolean = true;

    onMount(async () => {
        new SettingsApi(getPrivateApiConfig()).getIntroductions()
            .then((response) => {
                    loading = false;
                    firstName = response.introductorySettings!.firstName!;
                }
            ).catch(async (error: ResponseError) => {
            await getErrorMessageFromSdk(error)
                .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
        })
    })
    let firstName: string = "...";

</script>

<div class="bg-neutral-800 py-8 px-4 flex flex-col gap-4 rounded-b-xl">
    {#if !loading}
        <h1>Hello, {firstName}.</h1>
        <p class="text-neutral-300 font-semibold">Did you know that Octopuses have three hearts, and two of them stop
            beating when they swim!</p>
    {:else}
        <div class="flex w-full justify-center">
            <Spinner></Spinner>
        </div>
    {/if}
    <Button on:click={doLogOut}>Log Out</Button>
</div>