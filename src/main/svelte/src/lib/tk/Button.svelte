<script lang="ts">
</script>

<button aria-label="Button" on:click><slot></slot></button>

<style lang="postcss">
    button {
        @apply w-full;
        @apply font-semibold;
        @apply px-5 py-2 rounded-lg;
        @apply text-neutral-300;
        @apply dark:bg-neutral-700;
        @apply hover:dark:bg-neutral-600/70;
        @apply active:dark:bg-neutral-600/95;
        @apply focus:ring-2 focus:ring-neutral-400;
        @apply transition-all;
    }
</style>