<script lang="ts">
    import Nav from "$lib/components/Nav.svelte";
</script>

<Nav>
    <div class="flex flex-col items-center min-h-full max-w-screen">
        <div class="flex flex-col p-8 min-h-full w-screen max-w-screen xl:min-w-[50rem] xl:w-[50rem] gap-6 overflow-x-clip">
            <slot></slot>
        </div>
    </div>
</Nav>