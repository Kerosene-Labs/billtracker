<script lang="ts">
    import {toastQueue} from "$lib/toast";
    import Toast from "$lib/tk/Toast.svelte";
</script>

<div class="absolute flex flex-col gap-2 p-2 z-10">
    {#each $toastQueue as toastRequest, index}
        <Toast request={toastRequest} id={index}></Toast>
    {/each}
</div>