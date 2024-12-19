<script lang="ts">
    import {errorQueue} from "$lib";
    import {onMount} from "svelte";

    export let message: string;
    export let id: number;

    onMount(() => {

    })

    export function destroy() {
        errorQueue.update(errors => errors.filter((_, index) => index !== id));
    }
</script>

<button
        title="Dismiss"
        class="p-4 bg-red-900 text-red-300 hover:bg-red-800 hover:text-red-200 transition-colors font-mono text-sm
            rounded-lg drop-shadow-2xl max-w-72 text-left"
        on:click={destroy}
>
    {message}
</button>
