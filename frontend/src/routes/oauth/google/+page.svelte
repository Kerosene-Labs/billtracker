<script lang="ts">
  import Card from "$lib/tk/Card.svelte";
  import { onMount } from "svelte";
  import Spinner from "$lib/tk/Spinner.svelte";
  import { OAuth2Api, ResponseError } from "$lib/sdk/index.js";
  import {
    getPrivateApiConfig,
    getErrorMessageFromSdk,
    getPublicApiConfig,
  } from "$lib/sdkUtil";
  import { goto } from "$app/navigation";

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

{#if !errorMessage}
  <Card>
    <div class="flex flex-col xl:flex-row">
      <div class="flex w-fit flex-col">
        <h1>Welcome</h1>
        <p class="subtitle desktop:text-nowrap">
          Hang tight while we put the finishing touches on your account.
        </p>
      </div>
      <div class="flex w-full items-center justify-center">
        <Spinner></Spinner>
      </div>
    </div>
  </Card>
{:else}
  <Card title="Oops" subtitle="Something went wrong. Please report this.">
    <p class="font-mono font-bold text-neutral-400">{errorMessage}</p>
  </Card>
{/if}
