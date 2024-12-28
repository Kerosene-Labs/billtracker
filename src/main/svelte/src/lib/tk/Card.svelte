<script lang="ts">
  export let title: string | undefined = undefined;
  export let subtitle: string | undefined = undefined;
</script>

<div class="card drop-shadow-2xl">
  {#if title || subtitle}
    <div class="pb-4">
      {#if title}
        <h1>{title}</h1>
      {/if}
      {#if subtitle}
        <p class="subtitle pt-2">{subtitle}</p>
      {/if}
    </div>
  {/if}
  <slot></slot>
</div>

<style lang="postcss">
  .card {
    @apply rounded-2xl p-8;
    @apply bg-neutral-900/35;
    @apply w-full;
    @apply break-words;
    @apply drop-shadow-xl;
  }
</style>
