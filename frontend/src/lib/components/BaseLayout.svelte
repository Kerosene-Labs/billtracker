<script lang="ts">
  export let justifyCenter: boolean = true;
</script>

<div
  class="max-w-screen flex min-h-full flex-col items-center"
  class:justify-center={justifyCenter}
>
  <div
    class="max-w-screen flex min-h-full w-screen flex-col gap-6 p-8 xl:w-[50rem] xl:max-w-[50rem]"
  >
    <slot></slot>
  </div>
</div>
