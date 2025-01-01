<script lang="ts">
  import { onMount } from "svelte";
  import { toastQueue, type ToastRequest, ToastType } from "$lib/toast";
  import { cubicOut } from "svelte/easing";
  import { fly } from "svelte/transition";
  export let request: ToastRequest;
  export let id: number;

  onMount(() => {
    setTimeout(() => {
      destroy();
    }, 5000);
  });

  export function destroy() {
    toastQueue.update((toasts) => toasts.filter((_, index) => index !== id));
  }
</script>

<button
  transition:fly={{ x: -100, duration: 150, delay: 25, easing: cubicOut }}
  title="Dismiss"
  class="min-w-72 max-w-72 rounded-lg p-6 text-left font-mono text-sm font-bold drop-shadow-2xl transition-colors"
  on:click={destroy}
  class:error={request.type === ToastType.ERROR}
  class:success={request.type === ToastType.SUCCESS}
  class:info={request.type === ToastType.INFO}
>
  {request.message}
</button>

<style lang="postcss">
  .error {
    @apply bg-red-900 text-red-300 hover:bg-red-800 hover:text-red-200;
  }

  .success {
    @apply bg-green-900 text-green-300 hover:bg-green-800 hover:text-green-200;
  }

  .info {
    @apply bg-neutral-950 text-neutral-300 hover:bg-neutral-900 hover:text-neutral-200;
  }
</style>
