<script lang="ts">
    import Nav from "$lib/components/Nav.svelte";
</script>

<Nav>
    <div class="flex flex-col items-center min-h-full">
        <div class="flex flex-col p-8 min-h-full min-w-[50rem] gap-6">
            <slot></slot>
        </div>
    </div>
</Nav>