<script lang="ts">
  export let title: string | undefined = undefined;
  export let subtitle: string | undefined = undefined;
</script>

<div class="bg-card rounded-2xl p-8">
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