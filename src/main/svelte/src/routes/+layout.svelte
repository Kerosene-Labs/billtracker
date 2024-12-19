<script lang="ts">
	import '../app.css';
    import ErrorCard from "$lib/tk/ErrorQueue.svelte";

	let { children } = $props();
</script>

<ErrorCard></ErrorCard>

{@render children()}
