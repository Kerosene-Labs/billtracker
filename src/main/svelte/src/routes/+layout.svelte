<script lang="ts">
	import '../app.css';
    import ToastQueue from "$lib/tk/ToastQueue.svelte";

	let { children } = $props();
</script>

<ToastQueue></ToastQueue>

{@render children()}
