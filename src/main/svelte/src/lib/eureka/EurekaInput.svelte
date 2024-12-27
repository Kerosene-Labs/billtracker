<script lang="ts">
  export let id: string;
  export let label: string;
  export let type: InputTypes;
  export let value: string | Date | number | null = null;
  export let suffix: string | undefined = undefined;
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

  $: console.log(value);
</script>

<div class="eureka-input">
  <label
    class="text-sm font-semibold uppercase text-neutral-600"
    for={id}
    class:no-focus-label-shift={value === null}
    class:shift-label={value !== null}>{label}</label
  >
  <input bind:value {id} {type} style="all: unset;" />
  {#if suffix}
    <div class="eureka-input-suffix">
      <p>{suffix}</p>
    </div>
  {/if}
</div>

<style lang="postcss">
  .eureka-input {
    @apply flex h-10 w-fit items-center rounded-lg p-4 transition-all;
    @apply font-semibold;
    @apply bg-neutral-800 text-neutral-300 hover:bg-neutral-700/70 focus:bg-neutral-700/95;
    @apply focus-within:ring-2 focus-within:ring-neutral-400;
    @apply mt-4;
  }

  .eureka-input-suffix p {
    @apply bg-neutral-700;
  }

  .eureka-input label {
    @apply origin-left translate-y-0 scale-100 transition-transform;
    @apply absolute;
  }

  .eureka-input:focus-within label.no-focus-label-shift {
    @apply translate-x-[-20%] translate-y-[-175%];
  }

  .eureka-input label.shift-label {
    @apply translate-x-[-20%] translate-y-[-175%];
  }
</style>
