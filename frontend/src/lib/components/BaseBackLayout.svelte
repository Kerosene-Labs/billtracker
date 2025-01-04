<script lang="ts">
  import Back from "$lib/components/Back.svelte";

  interface Props {
    title?: String;
    children?: any;
  }

  let { title, children }: Props = $props();
</script>

<Back {title}>
  <div class="max-w-screen flex min-h-full flex-col items-center">
    <div id="layout"
      class="max-w-screen flex min-h-full w-screen flex-col gap-6 overflow-x-clip p-8 xl:w-[50rem] xl:min-w-[50rem]"
    >
      {@render children()}
    </div>
  </div>
</Back>
