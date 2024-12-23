<script lang="ts">
    import Nav from "$lib/components/Nav.svelte";

    export let justifyCenter: boolean = true;
</script>

<div class="flex flex-col items-center min-h-full" class:justify-center={justifyCenter}>
    <div class="flex flex-col  p-8 min-h-full gap-6 xl:w-[50rem] xl:max-w-[50rem]">
        <slot></slot>
    </div>
</div>
