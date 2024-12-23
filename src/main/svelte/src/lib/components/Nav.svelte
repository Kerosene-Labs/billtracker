<script lang="ts">

    export let drawerOpen: boolean = false;

    function toggleDrawer() {
        drawerOpen = !drawerOpen;
    }
</script>

<!--please note that our main will clip any content that is overflowing in an effort to maintain a decent user experience-->
<main class="min-h-screen h-screen max-h-screen overflow-clip">
    <div id="appBar" class="absolute bg-neutral-900 h-14 max-h-14 w-screen z-30 drop-shadow-lg flex flex-row items-center px-4">
        <button aria-label="App Drawer Toggle" on:click={toggleDrawer}>
            <svg class="h-10 fill-neutral-300" viewBox="0 0 28 28" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M4 7C4 6.44771 4.44772 6 5 6H24C24.5523 6 25 6.44771 25 7C25 7.55229 24.5523 8 24 8H5C4.44772 8 4 7.55229 4 7Z"
                />
                <path d="M4 13.9998C4 13.4475 4.44772 12.9997 5 12.9997L16 13C16.5523 13 17 13.4477 17 14C17 14.5523 16.5523 15 16 15L5 14.9998C4.44772 14.9998 4 14.552 4 13.9998Z"
                />
                <path d="M5 19.9998C4.44772 19.9998 4 20.4475 4 20.9998C4 21.552 4.44772 21.9997 5 21.9997H22C22.5523 21.9997 23 21.552 23 20.9998C23 20.4475 22.5523 19.9998 22 19.9998H5Z"
                />
            </svg>
        </button>
    </div>
        <div id="drawer" class="inset-y-y fixed h-screen w-[90%] lg:w-[25%] bg-neutral-950 z-20 translate-x-[-100%] transition-all" class:translate-x-[0%]={drawerOpen}></div>
        <div id="drawerBg" class="inset-y-0 fixed bg-black/50 w-screen h-screen z-10 backdrop-blur-sm transition-all" class:opacity-0={!drawerOpen} class:opacity-[99%]={drawerOpen}></div>
    <div id="pageContent" class="pt-14 h-full overflow-y-scroll">
        <slot></slot>
    </div>
</main>