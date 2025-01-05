<script lang="ts">
  import { ResponseError, SettingsApi } from "$lib/sdk";
  import { getPrivateApiConfig, getErrorMessageFromSdk } from "$lib/sdkUtil";
  import { addToToastQueue, EButton, ECard, EDateInput, ETextInput, ToastType } from "@kerosenelabs/eureka";
  import { onMount } from "svelte";

  let firstName: string;
  let lastName: string;
  let birthday: Date;

  async function populate() {
    await new SettingsApi(getPrivateApiConfig())
      .getIntroductions()
      .then((response) => {
        firstName = response.introductorySettings!.firstName!;
        lastName = response.introductorySettings!.lastName!;
        birthday = response.introductorySettings!.birthday!;
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
  }

  // calls the getIntroductions endpoint to determine if we're already set
  onMount(async () => {
    await populate();
  });

  async function save() {
    await new SettingsApi(getPrivateApiConfig())
      .setIntroductions({
        setIntroductorySettingsRequest: {
          firstName: firstName,
          lastName: lastName,
          birthday: new Date(birthday),
        },
      })
      .then(() => {
        addToToastQueue({
          message: `Nice to meet you, ${firstName}. We've saved this to your account.`,
          type: ToastType.SUCCESS,
        });
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
  }
</script>

<ECard title="Introductions" subtitle="Tell us a bit about yourself.">
  <div class="flex flex-col gap-4">
    <div class="flex flex-col gap-4 xl:flex-row">
      <ETextInput bind:value={firstName} id="firstName" label="First Name"
      ></ETextInput>
      <ETextInput bind:value={lastName} id="lastName" label="Last Name"
      ></ETextInput>
      <EDateInput bind:value={birthday} id="birthday" label="Birthday"
      ></EDateInput>
    </div>
    <EButton onclick={save}>Save</EButton>
  </div>
</ECard>

<style lang="postcss">
</style>
