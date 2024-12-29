<script lang="ts">
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
</script>

<div class="flex justify-center h-screen w-screen p-6 xl:pt-24">
  <div
    class="flex flex-col gap-4  text-center justify-center  items-center bg-neutral-900/30 h-fit rounded-3xl p-12 shadow-xl hover:shadow-2xl hover:scale-[101%] max-w-[50rem] hover:bg-neutral-900/30 transition-all">
    <h1 class="text-3xl xl:text-5xl">Introducing BillTracker</h1>
    <p class="text-neutral-400 font-semibold">Your new personal money tracking app. Built on the foundations of the MIT
      license, it's transparent, auditable
      and built to be usable for everybody.</p>
  </div>
</div>