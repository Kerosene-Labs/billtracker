<script lang="ts">
  import Button from "$lib/tk/Button.svelte";

  export let headers: String[] = ["Col 1", "Col 2", "Col 3"];
  export let rows: String[][] = [["Val 1", "Val 2", "Val 3"]];
</script>

<div class="flex w-full overflow-auto rounded-xl bg-neutral-800">
  <table class="w-full table-auto">
    <thead class="bg-neutral-900">
      <tr>
        {#each headers as header}
          <th class="p-4 font-mono text-sm font-bold text-neutral-400"
            >{header}</th
          >
        {/each}
      </tr>
    </thead>
    <tbody>
      {#each rows as row}
        <tr
          class="transition-colors hover:bg-neutral-700/20 active:bg-neutral-900/50"
        >
          {#each row as cell}
            <td class="p-4 text-center font-semibold text-neutral-200"
              >{cell}</td
            >
          {/each}
        </tr>
      {/each}
    </tbody>
  </table>
</div>

<style lang="postcss">
</style>
