<script lang="ts">
  import { page } from "$app/state";
  import RecurringExpenseEventCreatorEditor from "../RecurringExpenseEventCreatorEditor.svelte";
  import Modal from "$lib/tk/Modal.svelte";
  import Button from "$lib/tk/Button.svelte";
  import { onMount } from "svelte";

  onMount(() => {
    document.title = "Modify Recurring Expense | BillTracker";
  })

  let modal = false;
</script>

<RecurringExpenseEventCreatorEditor id={page.params.id}
></RecurringExpenseEventCreatorEditor>
