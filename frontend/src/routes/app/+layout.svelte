<script lang="ts">
  import { ERootPage } from "@kerosenelabs/eureka";
  import NavItems from "$lib/components/NavItems.svelte";
  let { children } = $props();
</script>

<ERootPage>
  {#snippet navItems()}
    <NavItems></NavItems>
  {/snippet}
  {@render children()}
</ERootPage>
