<script lang="ts">
  interface Props {
    children?: any;
    onclick?: any;
  }

  let { children, onclick }: Props = $props();
</script>

<button
  {onclick}
  class="p-4 pl-4 text-left text-xl font-semibold text-neutral-800 transition-colors hover:bg-neutral-300 dark:text-neutral-200 dark:hover:bg-neutral-800">
  {@render children()}
</button>
