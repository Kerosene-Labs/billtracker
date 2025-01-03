<script lang="ts">
  import Back from "$lib/components/Back.svelte";
</script>

<Back>
  <div class="max-w-screen flex min-h-full flex-col items-center">
    <div
      class="max-w-screen flex min-h-full w-screen flex-col gap-6 overflow-x-clip p-8 xl:w-[50rem] xl:min-w-[50rem]"
    >
      <slot></slot>
    </div>
  </div>
</Back>
