<script lang="ts">
  export let row: string[];
</script>

<tr
  on:click
  class="transition-colors hover:bg-neutral-700/20 active:bg-neutral-900/50"
>
  {#each row as cell}
    <td class="p-4 text-center font-semibold text-neutral-200">{cell}</td>
  {/each}
</tr>
