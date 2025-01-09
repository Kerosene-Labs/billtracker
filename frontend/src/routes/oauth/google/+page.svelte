<script lang="ts">
  import { onMount } from "svelte";
  import { OAuth2Api, ResponseError } from "$lib/sdk/index.js";
  import { getErrorMessageFromSdk, getPublicApiConfig } from "$lib/sdkUtil";
  import { goto } from "$app/navigation";
  import { ECard, EH1, EP, ESpinner } from "@kerosenelabs/eureka";

  let errorMessage: string | undefined = undefined;

  onMount(async () => {
    document.title = "Setting up account | BillTracker";

    const urlParams = new URLSearchParams(window.location.search);
    const code = urlParams.get("code");
    await new OAuth2Api(getPublicApiConfig())
      .handleToken({ handleTokenRequest: { code: code! } })
      .then((response) => {
        sessionStorage.setItem("jwt", response.jwt!);
        goto("/app/home");
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) => (errorMessage = msg));
      });
  });
</script>

<div class="flex min-h-full flex-col items-center justify-center">
  <div class="flex max-w-96 flex-col">
    {#if !errorMessage}
      <ECard>
        <div class="flex flex-col xl:flex-row">
          <div class="flex w-fit flex-col">
            <EH1>Welcome</EH1>
            <EP
              >Hang tight while we put the finishing touches on your account.</EP>
          </div>
          <div class="flex w-full items-center justify-center">
            <ESpinner></ESpinner>
          </div>
        </div>
      </ECard>
    {:else}
      <ECard title="Oops" subtitle="Something went wrong. Please report this.">
        <p class="font-mono font-bold text-neutral-400">{errorMessage}</p>
      </ECard>
    {/if}
  </div>
</div>
