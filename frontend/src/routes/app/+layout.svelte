<script lang="ts">
  import BaseNavLayout from "$lib/components/BaseNavLayout.svelte";

  let { children } = $props();
</script>

<BaseNavLayout>
  {@render children()}
</BaseNavLayout>
