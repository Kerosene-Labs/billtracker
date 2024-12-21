<script lang="ts">
    import Button from "$lib/tk/Button.svelte";
    import Card from "$lib/tk/Card.svelte";

    const googleOauthUri = import.meta.env.VITE_GOOGLE_OAUTH_URI;

    // do oauth signin with google
    function doSignInWithGoogle() {
        window.location.href = googleOauthUri;
    }

</script>

<div class="flex flex-col justify-center items-center min-h-full">
    <div class="flex flex-col max-w-screen-xl">
        <Card title="Welcome" subtitle="We're so excited to have you.">
            <div class="flex flex-col gap-2">
                <Button on:click={doSignInWithGoogle}>Sign in with Google</Button>
                <Button on:click={doSignInWithGoogle}>Sign in with Apple</Button>
                <Button on:click={doSignInWithGoogle}>Sign in with Microsoft</Button>
            </div>
        </Card>
    </div>
</div>