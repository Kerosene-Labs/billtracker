<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import {doLogOut, getErrorMessageFromSdk, getPrivateApiConfig} from "$lib/sdkUtil";
    import {onMount} from "svelte";
    import {ResponseError, SettingsApi} from "$lib/sdk";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import Card from "$lib/tk/Card.svelte";

    onMount(async () => {
        new SettingsApi(getPrivateApiConfig()).getIntroductions()
            .then((response) => {
                    firstName = response.introductorySettings!.firstName!;
                    // lastName = response.introductorySettings!.lastName!;
                    // birthday = response.introductorySettings!.birthday!;
                }
            ).catch(async (error: ResponseError) => {
            await getErrorMessageFromSdk(error)
                .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
        })
    })
    let firstName: string = "...";

</script>

<div class="bg-neutral-800 py-8 px-4 flex flex-col gap-2">
    <h1>Hello, {firstName}.</h1>
    <p class="text-neutral-300 font-semibold">Did you know that Octopuses have three hearts, and two of them stop beating when they swim!</p>
    <Button on:click={doLogOut}>Log Out</Button>
</div>