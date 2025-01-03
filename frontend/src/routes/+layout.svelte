<script lang="ts">
  import "../app.css";
  import ToastQueue from "$lib/tk/ToastQueue.svelte";
  import { onNavigate } from "$app/navigation";

  let { children } = $props();

</script>

<ToastQueue></ToastQueue>
{@render children()}
