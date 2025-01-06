<script lang="ts">
  import {
    doLogOut,
    getErrorMessageFromSdk,
    getPrivateApiConfig,
  } from "$lib/sdkUtil";
  import { onMount } from "svelte";
  import { ResponseError, SettingsApi } from "$lib/sdk";
  import {
    addToToastQueue,
    EButton,
    ECard,
    EH1,
    EP,
    ESpinner,
    ToastType,
  } from "@kerosenelabs/eureka";

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

<div class="m-4">
  <ECard>
    {#if !loading}
      <EH1>Hey, {firstName}!</EH1>
      <EP>Hope you're ready to save some money!</EP>
    {:else}
      <div class="flex w-full justify-center">
        <ESpinner></ESpinner>
      </div>
    {/if}
    <div class="pt-2">
      <EButton onclick={doLogOut}>Log Out</EButton>
    </div>
  </ECard>
</div>
