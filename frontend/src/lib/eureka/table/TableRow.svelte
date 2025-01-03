<script lang="ts">
  export let row: string[];
</script>

<tr
  on:click
  class="transition-colors border bg-neutral-200 hover:bg-neutral-300 active:bg-zinc-400 border-stone-300/50 dark:bg-neutral-700 hover:dark:bg-neutral-600 active:dark:bg-zinc-700 dark:border-stone-600/50"
>
  {#each row as cell}
    <td class="p-4 text-center font-semibold text-neutral-800 dark:text-neutral-300">{cell}</td>
  {/each}
</tr>
