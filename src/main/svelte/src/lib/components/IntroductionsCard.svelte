<script lang="ts">

    import LineEdit from "$lib/tk/LineEdit.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {ResponseError, SettingsApi} from "$lib/sdk";
    import {apiConfig, getErrorMessageFromSdk} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import {onMount} from "svelte";
    import Card from "$lib/tk/Card.svelte";
    import {goto} from "$app/navigation";

    // swaps the editor out with some placeholder content if the introductory settings are already set
    export let hideIfPopulated: boolean = false;

    let hidden: boolean = false;
    let firstName: string;
    let lastName: string;
    let birthday: string;

    // calls the getIntroductions endpoint to determine if we're already set
    onMount(async () => {
        if (hideIfPopulated) {
            await new SettingsApi(apiConfig).getIntroductions()
                .then((response) => {
                    if (response.introductorySettings!.firstName != null) {
                        hidden = true;
                    } else {
                        firstName = response.introductorySettings!.firstName;
                        lastName = response.introductorySettings!.lastName;
                        birthday = response.introductorySettings!.birthday;
                    }
                }).catch(async (error: ResponseError) => {
                    await getErrorMessageFromSdk(error)
                        .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
                })
        }
    })

    async function doSaveIntroductions() {
        await new SettingsApi(apiConfig).setIntroductions({
            setIntroductorySettingsRequest: {
                firstName: firstName,
                lastName: lastName,
                birthday: new Date(birthday)
            }
        }).then(() => {
            addToToastQueue({
                message: `Nice to meet you, ${firstName}. We've saved this to your account.`,
                type: ToastType.SUCCESS
            })
        }).catch(async (error: ResponseError) => {
            await getErrorMessageFromSdk(error)
                .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
        })
    }
</script>

{#if !hidden}
    <Card title="Introductions" subtitle="Tell us a bit about yourself.">
        <div class="flex flex-col gap-4">
            <div class="flex flex-col xl:flex-row gap-4">
                <LineEdit bind:value={firstName} id="firstName" label="First Name" type="text"></LineEdit>
                <LineEdit bind:value={lastName} id="lastName" label="Last Name" type="text"></LineEdit>
                <LineEdit bind:value={birthday} id="firstName" label="Birthday" type="date"></LineEdit>
            </div>
            <Button on:click={doSaveIntroductions}>Save</Button>
        </div>
    </Card>
{:else}
    <Card title="Introductions"
          subtitle="You've already introduced yourself. If you need to modify it, click the button below to navigate to your settings.">
        <Button on:click={() => {goto("/settings")}}>Go To Settings</Button>
    </Card>
{/if}

<style lang="postcss">
</style>