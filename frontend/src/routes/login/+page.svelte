<script lang="ts">
  import { EButton, ECard } from "@kerosenelabs/eureka";

  const googleOauthUri = import.meta.env.VITE_GOOGLE_OAUTH_URI;

  // do oauth signin with google
  function doSignInWithGoogle() {
    window.location.href = googleOauthUri;
  }
</script>

<div class="flex min-h-full flex-col items-center justify-center">
  <div class="flex max-w-96 flex-col">
    <ECard
      title="Welcome"
      subtitle="We're so excited to have you. Please log in with one of the following providers.">
      <div class="flex flex-col gap-2">
        <EButton onclick={doSignInWithGoogle}>Sign in with Google</EButton>
        <EButton disabled={true}>Sign in with Apple</EButton>
        <EButton disabled={true}>Sign in with Microsoft</EButton>
        <EButton disabled={true}>Sign in with E-Mail</EButton>
      </div>
    </ECard>
  </div>
</div>
