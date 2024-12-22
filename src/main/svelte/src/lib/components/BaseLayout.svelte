<script lang="ts">
    export let justifyCenter: boolean = true;
</script>

<div class="flex flex-col items-center min-h-full" class:justify-center={justifyCenter}>
    <div class="flex flex-col xl:min-w-[50rem] xl:max-w-[50rem] p-8 min-h-full gap-6">
        <slot></slot>
    </div>
</div>