<script>
  import Card from "$lib/tk/Card.svelte";
  import ExpenseEventTable from "$lib/components/expenses/ExpenseEventTable.svelte";
  import RecurringExpenseEventCreatorTable from "./recurring/RecurringExpenseTable.svelte";
  import { onMount } from "svelte";
  import BaseNavLayout from "$lib/components/BaseNavLayout.svelte";

  onMount(() => {
    document.title = "Expenses | BillTracker";
  });
</script>

<BaseNavLayout>
  <Card
    title="Introduction"
    subtitle="Expenses are things that take money out of your bank account. These range from expenses that recur on a fixed interval, like your mortgage, rent, or cell phone bill. One-off expenses don't have a fixed schedule, like dinner with a friend."
  ></Card>
  <Card
    title="Posted Expenses"
    subtitle="These should appear as charges in your bank account."
  >
    <div class="flex flex-col gap-2">
      <ExpenseEventTable></ExpenseEventTable>
    </div>
  </Card>
  <Card
    title="Recurring Expenses"
    subtitle="Expenses that occur on a fixed schedule, like your mortgage. We can take care of posting these, and when they do, you'll see them under your Posted Expenses."
  >
    <div class="flex flex-col gap-2">
      <RecurringExpenseEventCreatorTable></RecurringExpenseEventCreatorTable>
    </div>
  </Card>
</BaseNavLayout>