<script lang="ts">

    import Card from "$lib/tk/Card.svelte";
    import {onMount} from "svelte";
    import Spinner from "$lib/tk/Spinner.svelte";
    import {OAuth2Api, ResponseError} from "$lib/sdk/index.js";
    import {getErrorMessageFromSdk} from "$lib/sdkUtil";
    import {addToToastQueue, ToastType} from "$lib/toast";
    import {goto} from "$app/navigation";

    onMount(async () => {
        document.title = "Setting up account | BillTracker"

        const urlParams = new URLSearchParams(window.location.search)
        const code = urlParams.get("code");
        await new OAuth2Api().handleToken({handleTokenRequest: {code: code!}})
            .then((response) => {
                sessionStorage.setItem("jwt", response.jwt!)
                goto("/home");
            })
            .catch(async (error: ResponseError) => {
                await getErrorMessageFromSdk(error)
                    .then(msg => addToToastQueue({message: msg, type: ToastType.ERROR}))
            })
    })
</script>

<Card>
    <div class="flex flex-row">
        <div class="flex flex-col w-fit">
            <h1>Welcome, Googler!</h1>
            <p class="subtitle text-nowrap">Hang tight while we put the finishing touches on your account.</p>
        </div>
        <div class="flex items-center w-full justify-center">
            <Spinner></Spinner>
        </div>
    </div>
</Card>