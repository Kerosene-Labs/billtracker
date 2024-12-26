<script lang="ts">
    export let justifyCenter: boolean = true;
</script>

<div class="flex flex-col items-center min-h-full max-w-screen" class:justify-center={justifyCenter}>
    <div class="flex flex-col  p-8 min-h-full gap-6 w-screen max-w-screen xl:w-[50rem] xl:max-w-[50rem]">
        <slot></slot>
    </div>
</div>
