<script lang="ts">

    import LineEdit from "$lib/tk/LineEdit.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {ResponseError, SettingsApi} from "$lib/sdk";
    import {getPrivateApiConfig, getErrorMessageFromSdk} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import {onMount} from "svelte";
    import Card from "$lib/tk/Card.svelte";
    import {goto} from "$app/navigation";

    let firstName: string;
    let lastName: string;
    let birthday: Date;

    async function populate() {
        await new SettingsApi(getPrivateApiConfig()).getIntroductions()
            .then((response) => {
                    firstName = response.introductorySettings!.firstName!;
                    lastName = response.introductorySettings!.lastName!;
                    birthday = response.introductorySettings!.birthday!;
                }
            ).catch(async (error: ResponseError) => {
                await getErrorMessageFromSdk(error)
                    .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
            })
    }

    // calls the getIntroductions endpoint to determine if we're already set
    onMount(async () => {
        await populate();
    })

    async function save() {
        await new SettingsApi(getPrivateApiConfig()).setIntroductions({
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

<Card title="Introductions" subtitle="Tell us a bit about yourself.">
    <div class="flex flex-col gap-4">
        <div class="flex flex-col xl:flex-row gap-4">
            <LineEdit bind:value={firstName} id="firstName" label="First Name" type="text"></LineEdit>
            <LineEdit bind:value={lastName} id="lastName" label="Last Name" type="text"></LineEdit>
            <LineEdit bind:value={birthday} id="birthday" label="Birthday" type="date"></LineEdit>
        </div>
        <Button on:click={save}>Save</Button>
    </div>
</Card>
<Card title="Introductions"
      subtitle="You've already introduced yourself. If you need to modify it, click the button below to navigate to your settings.">
    <Button on:click={() => {goto("/settings")}}>Go To Settings</Button>
</Card>

<style lang="postcss">
</style>