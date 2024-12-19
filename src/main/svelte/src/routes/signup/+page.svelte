<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import Card from "$lib/tk/Card.svelte";
    import LineEdit from "$lib/tk/LineEdit.svelte";
    import {AuthControllerApi} from "$lib/sdk";
    import {goto} from "$app/navigation";
    import {addToErrorQueue} from "$lib";
    import {getErrorMessageFromSdk} from "$lib/sdkUtil";

    let email: string;
    let password: string;

    async function doSignUp() {
        await new AuthControllerApi().createUser({createUserRequest: {email: email, password: password}})
            .then((response) => {

            })
            .catch(async (error) => {
                await getErrorMessageFromSdk(error)
                    .then(msg => addToErrorQueue(msg))
            })
    }
</script>

<div class="flex flex-col items-center justify-center min-h-full">
    <Card>
        <div class="flex flex-col xl:flex-row gap-8">
            <div class="flex flex-col gap-2">
                <h2>Sign Up</h2>
                <LineEdit bind:value={email} id="email" type="email" label="E-Mail"></LineEdit>
                <LineEdit bind:value={password} id="password" type="password" label="Password"></LineEdit>
                <Button on:click={doSignUp}>Log In</Button>
            </div>
        </div>
    </Card>
</div>