<script lang="ts">
  import RecurringExpenseEventCreatorEditor from "./RecurringExpenseEditor.svelte";
  import { onMount } from "svelte";
  import { addToToastQueue, ToastType } from "$lib/toast";
  import { goto } from "$app/navigation";
  import BaseBackLayout from "$lib/components/BaseBackLayout.svelte";

  export let data;
  const id = data.id;

  onMount(() => {
    if (id == null) {
      addToToastQueue({
        message: "Unable to open the Recurring Expense editor. Unspecified ID.",
        type: ToastType.ERROR
      });
      goto("/app/expenses");
    }

    document.title = "Modify Recurring Expense | BillTracker";
  });
</script>

<!--this is kinda stinky, but oh well-->
<BaseBackLayout>
{#if id}
  <RecurringExpenseEventCreatorEditor {id}></RecurringExpenseEventCreatorEditor>
{/if}
</BaseBackLayout>