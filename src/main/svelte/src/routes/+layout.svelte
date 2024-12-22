<script lang="ts">
	import '../app.css';
    import ToastQueue from "$lib/tk/ToastQueue.svelte";

    const unprotectedRoutes: string[] = ["/login", "/signup", "/"]

    // afterNavigate(async (navigation) => {
    //     if (unprotectedRoutes.includes(window.location.href)) {
    //         return;
    //     }
    //     await validateAndEnforceSession();
    // });

	let { children } = $props();
</script>

<ToastQueue></ToastQueue>

{@render children()}
