<script>
  import RecurringExpenseCreator from "./RecurringExpenseCreator.svelte";
  import { onMount } from "svelte";

  onMount(() => {
    document.title = "Create Recurring Expense | BillTracker";
  })
</script>

<RecurringExpenseCreator></RecurringExpenseCreator>
