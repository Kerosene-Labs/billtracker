<script>

    import Card from "$lib/tk/Card.svelte";
    import {onMount} from "svelte";
    import Spinner from "$lib/tk/Spinner.svelte";

    onMount(() => {
        document.title = "Setting up account | BillTracker"
    })
</script>

<Card>
    <div class="flex flex-row">
        <div class="flex flex-col w-fit">
            <h1>Welcome</h1>
            <p class="subtitle text-nowrap">Hang tight while we put the finishing touches on your account.</p>
        </div>
        <div class="flex items-center w-full justify-center">
            <Spinner></Spinner>
        </div>
    </div>
</Card>