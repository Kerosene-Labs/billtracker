<script lang="ts">
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
  import Button from "$lib/tk/Button.svelte";
  import EHorizontalSeparator from "$lib/eureka/layout/EHorizontalSeparator.svelte";
</script>

<div class="absolute inset-0 flex h-screen w-screen items-center justify-center p-6 xl:pt-24 z-10 backdrop-blur-md">
  <div
    class="flex h-fit max-w-[50rem] flex-col items-center justify-center rounded-3xl bg-neutral-900/80 border border-neutral-800 backdrop-blur-2xl p-12 text-center"
  >
    <div class="flex flex-col pb-8">
      <h2 class="text-2xl desktop:text-3xl text-white font-semibold">Say Hello to...</h2>
      <h1 class="text-3xl desktop:text-6xl text-white">BillTracker</h1>
    </div>
    <EHorizontalSeparator></EHorizontalSeparator>
    <div class="flex flex-col gap-4 pt-8">
      <p class="font-semibold text-neutral-200">
        Your new personal money tracking app, built on the foundations of the MIT
        license, it's transparent, auditable and built to be usable for everybody.
      </p>
      <Button
        on:click={() => {
        goto("/login");
      }}>Try it Out
      </Button
      >
    </div>
  </div>
</div>
<video src="/bg.mp4" autoplay loop muted class="fullscreen-video"></video>

<style lang="postcss">
    .fullscreen-video {
        width: 100%; /* Optional: Adjust width to fit viewport */
        height: 100vh; /* Force the video to be 100% of viewport height */
        object-fit: cover; /* Ensures the video covers the entire area */
        position: fixed; /* Optional: Fix it to the viewport */
        top: 0;
        left: 0;
        z-index: -1; /* Optional: Send it to the background */
        @apply dark:opacity-20;
    }

</style>