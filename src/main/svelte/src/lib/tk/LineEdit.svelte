<script lang="ts">
  export let id: string;
  export let label: string;
  export let type: InputTypes;
  export let value: string | Date | number | undefined = undefined;
  export let min: number | null = null;
  export let max: number | null = null;
  export let title: string | null = null;

  function isWithinBounds(): boolean {
    if (value === undefined) {
      return true;
    } else if (typeof value === "number") {
      if (value < min! || value > max!) {
        return false;
      }
    }
    return true;
  }

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
  <label class="text-sm font-semibold uppercase text-neutral-600" for={id}
  >{label}</label
  >
  <input bind:value {id} {type} />
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

    input[type="number"]::-webkit-inner-spin-button,
    input[type="number"]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

    input[type="number"] {
        -moz-appearance: textfield; /* For Firefox */
    }
</style>
