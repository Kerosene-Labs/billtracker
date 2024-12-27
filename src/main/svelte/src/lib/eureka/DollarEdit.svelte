<script lang="ts">
  export let id: string;
  export let label: string;
  export let value: number;
</script>

<div class="eureka-input">
  <input type="text" style="all: unset;" />
</div>

<style lang="postcss">
  .eureka-input {
    @apply flex h-10 w-fit items-center rounded-xl p-4 transition-all;
    @apply font-semibold;
    @apply bg-neutral-800 text-neutral-300 hover:bg-neutral-700/70 focus:bg-neutral-700/95;
    @apply focus-within:ring-2 focus-within:ring-neutral-400;
  }
  .eureka-input-label {

  }
</style>
