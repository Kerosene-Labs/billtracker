<script lang="ts">
  import Button from "$lib/tk/Button.svelte";

  export let headers: String[] = ["Col 1", "Col 2", "Col 3"];
  export let rows: String[][] = [["Val 1", "Val 2", "Val 3"]];
</script>

<div class="flex bg-neutral-800 w-full rounded-xl overflow-auto">
  <table class="table-auto w-full">
    <thead class="bg-neutral-900">
    <tr>
      {#each headers as header}
        <th class="p-4 text-neutral-400 text-sm font-mono font-bold">{header}</th>
      {/each}
    </tr>
    </thead>
    <tbody>
    {#each rows as row}
      <tr class="hover:bg-neutral-700/20 active:bg-neutral-900/50 transition-colors">
        {#each row as cell}
          <td class="p-4 text-neutral-200 font-semibold text-center ">{cell}</td>
        {/each}
      </tr>
    {/each}
    </tbody>
  </table>
</div>

<style lang="postcss">

</style>