<script lang="ts">
  import { cubicOut } from "svelte/easing";
  import { fly, fade } from "svelte/transition";
  import Button from "$lib/tk/Button.svelte";

  interface Props {
    visible: boolean;
    title?: string;
    subtitle?: string;
    closeButtonVisible?: boolean;
  }

  let {visible = $bindable(false), title, subtitle, closeButtonVisible = true}: Props = $props();
</script>

{#if visible}
  <div class="fixed inset-0 w-screen h-screen bg-black/50 z-50 backdrop-blur-sm"
       transition:fade={{duration: 75}}>
    <div class="flex w-full h-full items-center justify-center">
      <div class="bg-neutral-800 p-8 rounded-lg w-[40rem] m-2"
           transition:fly={{ y: 20, duration: 150, delay: 25, easing: cubicOut, opacity: 50}}
      >
        <div class="flex flex-col gap-2">
          {#if title}
            <h1>{title}</h1>
          {/if}
          {#if subtitle}
            <p class="text-neutral-300 font-semibold">{subtitle}</p>
          {/if}
          <slot></slot>
          {#if closeButtonVisible}
            <Button on:click={() => {visible = !visible}}>Close</Button>
          {/if}
        </div>
      </div>
    </div>
  </div>
{/if}