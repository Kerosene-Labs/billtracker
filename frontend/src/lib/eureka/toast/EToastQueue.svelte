<script lang="ts">
  import { toastQueue } from "$lib/toast";
  import Toast from "$lib/eureka/toast/EToast.svelte";
</script>

<div
  class="absolute z-10 flex flex-col gap-2 p-2 drop-shadow-2xl w-screen h-screen items-center justify-end pointer-events-none overflow-clip"
>
  {#each $toastQueue as toastRequest, index}
    <Toast request={toastRequest} id={index}></Toast>
  {/each}
</div>
