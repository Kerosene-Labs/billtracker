<script lang="ts">
    import {errorQueue} from "$lib";
    import ErrorCard from "$lib/tk/ErrorCard.svelte";
</script>

<div class="absolute flex flex-col gap-2 p-2 z-10">
    {#each $errorQueue as error, index}
        <ErrorCard message={error} id={index}></ErrorCard>
    {/each}
</div>