<script lang="ts">
  import { page } from "$app/state";
  import RecurringExpenseEventCreatorEditor from "../RecurringExpenseEventCreatorEditor.svelte";

  document.title = "Modify Recurring Expense | BillTracker";
</script>

<RecurringExpenseEventCreatorEditor id={page.params.id}
></RecurringExpenseEventCreatorEditor>
