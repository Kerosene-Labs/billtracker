<script lang="ts">
  import Card from "$lib/tk/Card.svelte";
  import { onMount } from "svelte";
  import Spinner from "$lib/tk/Spinner.svelte";

  onMount(() => {
    document.title = "Home | BillTracker";
  })
</script>

<Card title="Projections" subtitle="There's nothing here yet. Check back soon."
></Card>
