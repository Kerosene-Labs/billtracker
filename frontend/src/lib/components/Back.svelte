<script lang="ts">
  interface Props {
    title?: String;
    children?: any;
  }
  let { title, children }: Props = $props();
</script>

<!--please note that our main will clip any content that is overflowing in an effort to maintain a decent user experience-->
<main class="h-screen max-h-screen min-h-screen overflow-clip">
  <div
    id="backBar"
    class="absolute z-30 flex h-14 max-h-14 w-screen flex-row items-center gap-8 bg-inherit px-4 backdrop-blur-lg">
    <button
      aria-label="App Drawer Toggle"
      onclick={() => {
        history.back();
      }}>
      <span
        class="text-2xl font-semibold text-neutral-300 transition-colors hover:text-neutral-400 dark:text-neutral-600 dark:hover:text-neutral-500"
        >&lt;</span>
    </button>
    <span
      class="font-semibold text-neutral-800 desktop:text-xl dark:text-neutral-400"
      >{title}</span>
  </div>
  <div id="pageContent" class="h-full overflow-y-auto pt-14">
    {@render children()}
  </div>
</main>
