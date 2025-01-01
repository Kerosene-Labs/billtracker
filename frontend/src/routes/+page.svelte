<script lang="ts">
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
  import Button from "$lib/tk/Button.svelte";
</script>

<div class="flex h-screen w-screen justify-center p-6 xl:pt-24">
  <div
    class="flex h-fit max-w-[50rem] flex-col items-center justify-center gap-4 rounded-3xl bg-neutral-900/30 p-12 text-center shadow-xl transition-all hover:scale-[101%] hover:bg-neutral-900/30 hover:shadow-2xl"
  >
    <h1 class="text-3xl xl:text-5xl">Introducing BillTracker</h1>
    <p class="font-semibold text-neutral-400">
      Your new personal money tracking app. Built on the foundations of the MIT
      license, it's transparent, auditable and built to be usable for everybody.
    </p>
    <Button
      on:click={() => {
        goto("/login");
      }}>Try it Out</Button
    >
  </div>
</div>
