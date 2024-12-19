<script lang="ts">
    export let outerErrorText: string | undefined;
</script>

<button on:click={() => {outerErrorText = ""}}
        class="p-4 bg-red-900 text-red-300 hover:bg-red-800 hover:text-red-200 transition-colors font-mono text-sm rounded-lg drop-shadow-lg">
    <slot></slot>
</button>