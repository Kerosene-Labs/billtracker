<script lang="ts">
  import NavItems from "$lib/components/NavItems.svelte";
  import { ECard, ERootPage } from "@kerosenelabs/eureka";
  import { onMount } from "svelte";

  onMount(() => {
    document.title = "Home | BillTracker";
  });
</script>

<ECard title="Projections" subtitle="There's nothing here yet. Check back soon."
></ECard>
