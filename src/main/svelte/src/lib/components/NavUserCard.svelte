<script lang="ts">
  import Button from "$lib/tk/Button.svelte";
  import {
    doLogOut,
    getErrorMessageFromSdk,
    getPrivateApiConfig,
  } from "$lib/sdkUtil";
  import { onMount } from "svelte";
  import { ResponseError, SettingsApi } from "$lib/sdk";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import Spinner from "$lib/tk/Spinner.svelte";

  let loading: boolean = true;

  onMount(async () => {
    new SettingsApi(getPrivateApiConfig())
      .getIntroductions()
      .then((response) => {
        loading = false;
        firstName = response.introductorySettings!.firstName!;
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
  });
  let firstName: string = "...";
</script>

<div class="flex flex-col gap-4 rounded-b-xl bg-neutral-800 px-4 py-8">
  {#if !loading}
    <h1>Hello, {firstName}.</h1>
    <p class="font-semibold text-neutral-300">
      Did you know that Octopuses have three hearts, and two of them stop
      beating when they swim!
    </p>
  {:else}
    <div class="flex w-full justify-center">
      <Spinner></Spinner>
    </div>
  {/if}
  <Button on:click={doLogOut}>Log Out</Button>
</div>
