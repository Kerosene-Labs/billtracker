<script lang="ts">
  import RecurringExpenseEditor from "$lib/components/expenses/RecurringExpenseEditor.svelte";
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
  import BaseBackLayout from "$lib/components/BaseBackLayout.svelte";
  import { addToToastQueue, ToastType } from "@kerosenelabs/eureka";

  export let data;
  const id = data.id;

  onMount(() => {
    if (id == null) {
      addToToastQueue({
        message: "Unable to open the Recurring Expense editor. Unspecified ID.",
        type: ToastType.ERROR,
      });
      goto("/app/expenses");
    }

    document.title = "Modify Recurring Expense | BillTracker";
  });
</script>

<!--this is kinda stinky, but oh well-->
<BaseBackLayout title="Edit a Recurring Expense">
  {#if id}
    <RecurringExpenseEditor {id}></RecurringExpenseEditor>
  {/if}
</BaseBackLayout>
