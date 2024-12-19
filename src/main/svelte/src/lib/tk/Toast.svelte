<script lang="ts">
    import {onMount} from "svelte";
    import {toastQueue, type ToastRequest, ToastType} from "$lib/toast";

    export let request: ToastRequest;
    export let id: number;

    onMount(() => {
        setTimeout(() => {
            destroy()
        }, 5000)
    })

    export function destroy() {
        toastQueue.update(toasts => toasts.filter((_, index) => index !== id));
    }
</script>

<button
        title="Dismiss"
        class="p-4  transition-colors font-mono text-sm rounded-lg drop-shadow-2xl max-w-72 text-left"
        on:click={destroy}
        class:error={request.type === ToastType.ERROR}
        class:success={request.type === ToastType.SUCCESS}
        class:info={request.type === ToastType.INFO}
>
    {request.message}
</button>

<style lang="postcss">
    .error {
        @apply bg-red-900 text-red-300 hover:bg-red-800 hover:text-red-200;
    }

    .success {
        @apply bg-green-900 text-green-300 hover:bg-green-800 hover:text-red-200;
    }

    .info {
        @apply bg-neutral-900 text-neutral-300 hover:bg-neutral-800 hover:text-red-200;
    }
</style>
