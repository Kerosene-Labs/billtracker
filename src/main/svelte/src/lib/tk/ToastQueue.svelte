<script lang="ts">
    import {toastQueue} from "$lib/toast";
    import Toast from "$lib/tk/Toast.svelte";
</script>

<div class="fixed top-0 left-0 flex flex-col gap-2 p-2 pt-16 z-10 drop-shadow-2xl">
    {#each $toastQueue as toastRequest, index}
        <Toast request={toastRequest} id={index}></Toast>
    {/each}
</div>