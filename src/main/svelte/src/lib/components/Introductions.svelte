<script lang="ts">

    import LineEdit from "$lib/tk/LineEdit.svelte";
    import Button from "$lib/tk/Button.svelte";
    import {ResponseError, SettingsApi} from "$lib/sdk";
    import {apiConfig, getErrorMessageFromSdk} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import {goto} from "$app/navigation";

    let firstName: string;
    let lastName: string;
    let birthday: string;

    async function doSaveIntroductions() {
        await new SettingsApi(apiConfig).setIntroductions({
            request: {
                firstName: firstName,
                lastName: lastName,
                birthday: new Date(birthday),
            }
        }).then(() => {
            addToToastQueue({message: "Saved.", type: ToastType.SUCCESS})
        }).catch(async (error: ResponseError) => {
            await getErrorMessageFromSdk(error)
                .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
        })
    }
</script>

<div class="flex flex-col gap-4">
    <div class="flex flex-col xl:flex-row gap-4">
        <LineEdit bind:value={firstName} id="firstName" label="First Name" type="text"></LineEdit>
        <LineEdit bind:value={lastName} id="lastName" label="Last Name" type="text"></LineEdit>
        <LineEdit bind:value={birthday} id="firstName" label="Birthday" type="date"></LineEdit>
    </div>
    <Button on:click={doSaveIntroductions}>Save</Button>
</div>


<style lang="postcss">

</style>