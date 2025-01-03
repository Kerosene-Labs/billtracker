<script>
  import RecurringExpenseCreator from "./RecurringExpenseCreator.svelte";
  import { onMount } from "svelte";
  import BaseBackLayout from "$lib/components/BaseBackLayout.svelte";

  onMount(() => {
    document.title = "Create Recurring Expense | BillTracker";
  });
</script>

<RecurringExpenseCreator></RecurringExpenseCreator>
