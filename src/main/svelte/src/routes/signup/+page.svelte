<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import Card from "$lib/tk/Card.svelte";
    import LineEdit from "$lib/tk/LineEdit.svelte";
    import {AuthApi} from "$lib/sdk";
    import {apiConfig, getErrorMessageFromSdk} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";

    let email: string;
    let password: string;

    async function doSignUp() {
        await new AuthApi(apiConfig).createUser({createUserRequest: {email: email, password: password}})
            .then((response) => {
                addToToastQueue({message: "Success! Please check your email.", type: ToastType.SUCCESS})
            })
            .catch(async (error) => {
                await getErrorMessageFromSdk(error)
                    .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
            })
    }
</script>

<div class="flex flex-col items-center justify-center min-h-full">
    <Card title="Welcome" subtitle="Looking forward to work together.">
        <div class="flex flex-col gap-2">
            <LineEdit bind:value={email} id="email" type="email" label="E-Mail"></LineEdit>
            <LineEdit bind:value={password} id="password" type="password" label="Password"></LineEdit>
            <Button on:click={doSignUp}>Sign Up</Button>
        </div>
    </Card>
</div>