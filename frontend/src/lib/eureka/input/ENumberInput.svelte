<script lang="ts">
  interface Props {
    id: string;
    label: string;
    value: number;
    prefix?: string;
    required?: boolean;
    min?: number;
    max?: number;
  }

  let { id, label, value = $bindable(), prefix, required, min, max }: Props = $props();
</script>

{#if prefix}
  <div class="flex flex-col">
    <label class="text-sm font-semibold uppercase text-neutral-600" for={id}>{label}</label>
    <div class="flex flex-row items-center gap-2 rounded-lg bg-neutral-400 dark:bg-neutral-900/40 pl-4">
      <span class="font-mono font-bold text-neutral-500">{prefix}</span>
      <input bind:value {id} {min} {max} {required} type="number" />
    </div>
  </div>
{:else}
  <div class="flex flex-col">
    <label class="text-sm font-semibold uppercase text-neutral-600" for={id}>{label}</label>
    <input bind:value {id} {min} {max} {required} type="number" />
  </div>
{/if}

<style lang="postcss">
    input {
        @apply w-full;
        @apply rounded-lg px-5 py-2;
        @apply transition-all;
        @apply outline-none;
        @apply font-mono font-semibold;
        @apply text-neutral-800 dark:text-neutral-300;
        @apply bg-neutral-200 hover:bg-zinc-300 focus:bg-zinc-300;
        @apply dark:bg-neutral-900 hover:dark:bg-neutral-700 focus:bg-neutral-700;
    }

    input[type="number"]::-webkit-inner-spin-button,
    input[type="number"]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    input[type="number"] {
        -moz-appearance: textfield; /* For Firefox */
    }
</style>
