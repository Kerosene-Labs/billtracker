<script lang="ts">
    export let title: string | undefined = undefined;
    export let subtitle: string | undefined = undefined;
</script>

<div class="card">
    <div class="pb-2">
        {#if title}
            <h1>{title}</h1>
        {/if}
        {#if subtitle}
            <p class="subtitle">{subtitle}</p>
        {/if}
    </div>
    <slot></slot>
</div>

<style lang="postcss">
    .card {
        @apply p-8 rounded-xl;
        @apply drop-shadow-lg;
        @apply bg-neutral-900/35;
    }
</style>