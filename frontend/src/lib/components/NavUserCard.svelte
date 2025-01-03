<script lang="ts">
  import Button from "$lib/tk/Button.svelte";
  import {
    doLogOut,
    getErrorMessageFromSdk,
    getPrivateApiConfig
  } from "$lib/sdkUtil";
  import { onMount } from "svelte";
  import { ResponseError, SettingsApi } from "$lib/sdk";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import Spinner from "$lib/tk/Spinner.svelte";
  import Card from "$lib/tk/Card.svelte";

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
          addToToastQueue({ message: msg, type: ToastType.ERROR })
        );
      });
  });
  let firstName: string = "...";
</script>

<div class="m-4">
  <Card>
    {#if !loading}
      <h1>Hey, {firstName}!</h1>
      <p>Hope you're ready to save some money!</p>
    {:else}
      <div class="flex w-full justify-center">
        <Spinner></Spinner>
      </div>
    {/if}
    <Button on:click={doLogOut}>Log Out</Button>
  </Card>
</div>