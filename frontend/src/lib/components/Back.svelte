<script lang="ts">
  import NavItem from "$lib/components/NavItem.svelte";
  import NavUserCard from "$lib/components/NavUserCard.svelte";
  import { goto } from "$app/navigation";

  export let drawerOpen: boolean = false;

  function toggleDrawer() {
    drawerOpen = !drawerOpen;
  }
</script>

<!--please note that our main will clip any content that is overflowing in an effort to maintain a decent user experience-->
<main class="h-screen max-h-screen min-h-screen overflow-clip">
  <div
    id="backBar"
    class="absolute z-30 flex h-14 max-h-14 w-screen flex-row items-center bg-neutral-300 dark:bg-neutral-800 px-4 drop-shadow-lg"
  >
    <button aria-label="App Drawer Toggle" on:click={toggleDrawer}>
      <span class="font-bold text-2xl text-neutral-600">&lt;</span>
    </button>
  </div>
  <div id="pageContent" class="h-full overflow-y-auto pt-14">
    <slot></slot>
  </div>
</main>
