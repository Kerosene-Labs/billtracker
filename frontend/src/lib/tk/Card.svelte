<script lang="ts">
  export let title: string | undefined = undefined;
  export let subtitle: string | undefined = undefined;
</script>

<div class="bg-zinc-100 dark:bg-neutral-800 border-zinc-200/50 dark:border-zinc-700/50 border rounded-xl p-8">
  {#if title || subtitle}
    <div class="pb-4">
      <div class="flex flex-row items-center gap-4">
        {#if title}
          <h1>{title}</h1>
        {/if}
      </div>
      {#if subtitle}
        <p class="text-muted">{subtitle}</p>
      {/if}
    </div>
  {/if}
  <slot></slot>
</div>