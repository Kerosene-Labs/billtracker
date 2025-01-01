<script lang="ts">
  import Button from "$lib/tk/Button.svelte";
  import Card from "$lib/tk/Card.svelte";

  const googleOauthUri = import.meta.env.VITE_GOOGLE_OAUTH_URI;

  // do oauth signin with google
  function doSignInWithGoogle() {
    window.location.href = googleOauthUri;
  }
</script>

<div class="flex min-h-full flex-col items-center justify-center">
  <div class="flex max-w-96 flex-col">
    <Card
      title="Welcome"
      subtitle="We're so excited to have you. Please log in with one of the following providers."
    >
      <div class="flex flex-col gap-2">
        <Button on:click={doSignInWithGoogle}>Sign in with Google</Button>
        <Button disabled={true}>Sign in with Apple</Button>
        <Button disabled={true}>Sign in with Microsoft</Button>
        <Button disabled={true}>Sign in with E-Mail</Button>
      </div>
    </Card>
  </div>
</div>
