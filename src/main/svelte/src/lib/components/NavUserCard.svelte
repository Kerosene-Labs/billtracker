<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import {doLogOut, getErrorMessageFromSdk, getPrivateApiConfig} from "$lib/sdkUtil";
    import {onMount} from "svelte";
    import {ResponseError, SettingsApi} from "$lib/sdk";
    import {addToToastQueue, ToastType} from "$lib/toast";

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

<div class="bg-neutral-800 p-4 flex flex-col gap-4">
    <h1>Hello, {firstName}.</h1>
    <Button on:click={doLogOut}>Log Out</Button>
</div>