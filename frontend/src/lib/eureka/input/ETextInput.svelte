<script lang="ts">
  export let id: string;
  export let label: string;
  export let value: string | Date | number | undefined = undefined;
  export let prefix: string | undefined = undefined;
  export let required: boolean = false;
</script>

<div class="flex flex-col">
  <label class="text-sm font-semibold uppercase text-neutral-600" for={id}>{label}</label>
  {#if prefix}
    <div class="flex flex-row items-center gap-2 rounded-lg bg-neutral-900/40 pl-4">
      <span class="font-mono font-bold text-neutral-500">{prefix}</span>
      <input bind:value {id} {required} type="text" />
    </div>
  {:else}
    <input bind:value {id} {required} type="text" />
  {/if}
</div>

<style lang="postcss">
    input {
        @apply w-full;
        @apply rounded-lg px-5 py-2;
        @apply text-neutral-300;
        @apply bg-neutral-800;
        @apply hover:bg-neutral-700/70;
        @apply focus:bg-neutral-700/95;
        @apply focus:ring-2 focus:ring-neutral-400;
        @apply transition-all;
        @apply outline-none;
        @apply font-semibold;
    }
</style>
