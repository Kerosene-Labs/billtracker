<script lang="ts">
    export let text: String;
</script>

<button aria-label="Button">{text}</button>

<style lang="postcss">
    button {
        @apply font-semibold;
        @apply px-5 py-2 rounded-lg;
        @apply text-neutral-700 dark:text-neutral-300;
        @apply bg-neutral-300 dark:bg-neutral-700;
        @apply hover:bg-neutral-400 hover:dark:bg-neutral-600;
        @apply transition-all;
    }
</style>