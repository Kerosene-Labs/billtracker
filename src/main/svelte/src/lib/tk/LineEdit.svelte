<script lang="ts">
    export let id: string;
    export let label: string;
    export let type: InputTypes
    export let value: string;
    type InputTypes =
        | "button"
        | "checkbox"
        | "color"
        | "date"
        | "datetime-local"
        | "email"
        | "file"
        | "hidden"
        | "image"
        | "month"
        | "number"
        | "password"
        | "radio"
        | "range"
        | "reset"
        | "search"
        | "submit"
        | "tel"
        | "text"
        | "time"
        | "url"
        | "week";
</script>

<div class="flex flex-col">
    <label class="uppercase text-neutral-600 font-semibold text-sm" for="{id}">{label}</label>
    <input bind:value {id} {type}>
</div>

<style lang="postcss">
    input {
        @apply w-full;
        @apply px-5 py-2 rounded-lg;
        @apply text-neutral-300;
        @apply bg-neutral-800;
        @apply hover:bg-neutral-700/70;
        @apply focus:bg-neutral-700/95;
        @apply focus:ring-2 focus:ring-neutral-400;
        @apply transition-all;
        @apply outline-none;
    }
</style>