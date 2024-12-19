<script>
    import Button from "$lib/tk/Button.svelte";
    import Card from "$lib/tk/Card.svelte";
    import LineEdit from "$lib/tk/LineEdit.svelte";
    import {goto} from "$app/navigation";
</script>

<div class="flex flex-col items-center justify-center min-h-full">
    <Card>
        <div class="flex flex-col xl:flex-row gap-8">
            <div class="flex flex-col gap-2">
                <h2>Log In</h2>
                <LineEdit id="email" type="email" label="E-Mail"></LineEdit>
                <LineEdit id="password" type="password" label="Password"></LineEdit>
                <Button>Log In</Button>
            </div>
            <div class="flex flex-col gap-2 h-full justify-center">
                <h2>Or Join Us</h2>
                <Button on:click={() => {goto("/signup")}}>Sign Up</Button>
            </div>
        </div>
    </Card>
</div>