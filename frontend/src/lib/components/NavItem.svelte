<script lang="ts">
  interface Props {
    children?: any;
    onclick?: any;
  }

  let { children, onclick }: Props = $props();
</script>
<button onclick={onclick} class="p-4 pl-4 text-left text-xl font-semibold transition-colors text-neutral-800 dark:text-neutral-200 hover:bg-neutral-300 dark:hover:bg-neutral-800">
  {@render children()}
</button>