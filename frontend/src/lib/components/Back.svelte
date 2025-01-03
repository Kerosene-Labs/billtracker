<script lang="ts">
</script>

<!--please note that our main will clip any content that is overflowing in an effort to maintain a decent user experience-->
<main class="h-screen max-h-screen min-h-screen overflow-clip">
  <div
    id="backBar"
    class="absolute z-30 flex h-14 max-h-14 w-screen flex-row items-center px-4"
  >
    <button aria-label="App Drawer Toggle" on:click={() => {history.back()}}>
      <span class="font-bold text-2xl text-neutral-300 hover:text-neutral-400 dark:text-neutral-600 dark:hover:text-neutral-500 transition-colors">&lt;</span>
    </button>
  </div>
  <div id="pageContent" class="h-full overflow-y-auto pt-14">
    <slot></slot>
  </div>
</main>
