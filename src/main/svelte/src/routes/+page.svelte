<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import Card from "$lib/tk/Card.svelte";
    import LineEdit from "$lib/tk/LineEdit.svelte";
    import {goto} from "$app/navigation";
    import {AuthControllerApi, ResponseError} from "$lib/sdk";
    import ErrorCard from "$lib/tk/ErrorCard.svelte";

    // binds
    let email: string;
    let password: string;

    // request state
    let errorMessage: string | undefined = undefined;

    async function doLogin() {
        await new AuthControllerApi().createSession({
            createSessionRequest: {
                email: email,
                password: password
            }
        }).catch((error: ResponseError) => {
            errorMessage = error as unknown as string;
        }).finally(() => {
            if (errorMessage == undefined) {
                goto("/home");
            }
        })
    }
</script>

<div class="flex flex-col items-center justify-center min-h-full gap-2">
    {#if errorMessage}
        <ErrorCard bind:outerErrorText={errorMessage}>{errorMessage}</ErrorCard>
    {/if}
    <Card>
        <div class="grid grid-cols-1 xl:grid-cols-2 gap-12">
            <div class="flex flex-col gap-2">
                <div class="flex flex-col">
                    <h2>Log In</h2>
                    <p class="subtitle">We're happy you're back.</p>
                </div>
                <LineEdit id="email" type="email" label="E-Mail" bind:value={email}></LineEdit>
                <LineEdit id="password" type="password" label="Password" bind:value={password}></LineEdit>
                <Button on:click={doLogin}>Log In</Button>
            </div>
            <div class="flex flex-col gap-2 h-full justify-center">
                <div class="flex flex-col">
                    <h2>Or Join Us</h2>
                    <p class="subtitle">Join and start tracking your money.</p>
                </div>
                <Button on:click={() => {goto("/signup")}}>Sign Up</Button>
            </div>
        </div>
    </Card>
</div>