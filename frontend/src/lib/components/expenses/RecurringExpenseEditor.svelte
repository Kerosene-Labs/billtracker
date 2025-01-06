<script lang="ts">
  import {
    ExpensesApi,
    type RecurringExpenseEventCreator,
    ResponseError,
  } from "$lib/sdk";
  import { onMount } from "svelte";
  import { goto } from "$app/navigation";
  import { getErrorMessageFromSdk, getPrivateApiConfig } from "$lib/sdkUtil";
  import {
    addToToastQueue,
    EButton,
    ECard,
    EModal,
    ENumberInput,
    ESpinner,
    ETable,
    ETableRow,
    ETextInput,
    formatCurrency,
    getOrdinal,
    ToastType,
  } from "@kerosenelabs/eureka";

  // props
  export let id: string;

  // state
  let data: RecurringExpenseEventCreator | undefined = undefined;

  // supersede modal
  let supersedeModalVisible: boolean = false;
  let supersedeAmount: number;
  let supersedeDescription: string;
  let supersedeRecursEveryCalendarDay: number;
  let supersedeInFlight: boolean = false;

  // delete modal
  let deleteModalVisible: boolean = false;
  let deleteInFlight: boolean = false;

  // on mount call to load initial data
  onMount(() => {
    new ExpensesApi(getPrivateApiConfig())
      .getRecurringExpenseCreators({ ids: [id] })
      .then((response) => {
        data = response.recurringExpenseEventCreators[0];
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
  });

  /**
   * Calls the Expenses API to supersede this Recurring Expense. This function
   * will pre-emptively return out if our data is not yet set from the onMount.
   */
  function supersede() {
    if (!data) {
      return;
    }
    supersedeInFlight = true;
    new ExpensesApi(getPrivateApiConfig())
      .supersedeRecurringExpenseCreator({
        id: data.id,
        createRecurringExpenseCreatorRequest: {
          amount: supersedeAmount,
          description: supersedeDescription,
          recursEveryCalendarDay: supersedeRecursEveryCalendarDay,
        },
      })
      .then(() => {
        addToToastQueue({
          message: "Successfully superseded.",
          type: ToastType.SUCCESS,
        });
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
    supersedeInFlight = false;
    supersedeModalVisible = false;
    goto("/app/expenses");
  }

  /**
   * Calls the Expenses API to delete (AKA hide) this Recurring Expense.
   */
  function _delete() {
    if (!data) {
      return;
    }
    deleteInFlight = true;
    new ExpensesApi(getPrivateApiConfig())
      .deleteRecurringExpenseCreator({ id: data.id })
      .then(() => {
        addToToastQueue({
          message: "Successfully deleted.",
          type: ToastType.SUCCESS,
        });
      })
      .catch(async (error: ResponseError) => {
        await getErrorMessageFromSdk(error).then((msg) =>
          addToToastQueue({ message: msg, type: ToastType.ERROR }),
        );
      });
    deleteInFlight = false;
    deleteModalVisible = false;
    goto("/app/expenses");
  }
</script>

<EModal
  title="Supersede"
  subtitle="This will create a new iteration of this Recurring Expense. Enter the new details, and we'll handle the transition."
  closeButtonVisible={false}
  bind:visible={supersedeModalVisible}>
  <div class="flex flex-col gap-2">
    <ENumberInput
      id="amount"
      label="Amount"
      prefix="$"
      bind:value={supersedeAmount}></ENumberInput>
    <ETextInput
      id="description"
      label="Description"
      bind:value={supersedeDescription}></ETextInput>
    <ENumberInput
      id="calendarDay"
      label="Recurs every (Calendar Day)"
      bind:value={supersedeRecursEveryCalendarDay}></ENumberInput>
    <div class="flex flex-col gap-2 desktop:flex-row">
      <EButton
        type="secondary"
        onclick={() => {
          supersedeModalVisible = false;
        }}>Cancel</EButton>
      <EButton onclick={supersede} spinning={supersedeInFlight}
        >Continue</EButton>
    </div>
  </div>
</EModal>

<EModal
  title="Delete this?"
  subtitle="Are you sure? You'll be permanently deleting this recurring expense. Any posted expenses will remain."
  bind:visible={deleteModalVisible}
  closeButtonVisible={false}>
  <div class="flex flex-col gap-2 desktop:flex-row">
    <EButton
      type="secondary"
      onclick={() => {
        deleteModalVisible = false;
      }}>Cancel</EButton>
    <EButton type="danger" onclick={_delete} spinning={deleteInFlight}
      >Yes, delete this</EButton>
  </div>
</EModal>

<ECard title="Details" subtitle="In-depth details of this Recurring Expense.">
  {#if data}
    <div class="flex flex-col gap-2">
      <ETable headers={["Key", "Value"]}>
        <ETableRow row={["Amount", formatCurrency(data.amount)]}></ETableRow>
        <ETableRow row={["Description", data.description]}></ETableRow>
        <ETableRow
          row={[
            "Recurs every (Calendar Day)",
            getOrdinal(data.recursEveryCalendarDay),
          ]}></ETableRow>
      </ETable>
    </div>
  {:else}
    <ESpinner></ESpinner>
  {/if}
</ECard>
<ECard
  title="Actions"
  subtitle="Actions you can perform that change the state of this Recurring Expense.">
  <div class="flex flex-col gap-2 xl:flex-row">
    <EButton
      onclick={() => {
        supersedeModalVisible = true;
      }}
      title="Supersede this recurring expense">
      Supersede
    </EButton>
    <EButton
      onclick={() => {
        deleteModalVisible = true;
      }}
      type="danger"
      title="Delete this recurring expense. It will no longer post automatically, but existing posted expenses will remain.">
      Delete
    </EButton>
  </div>
</ECard>
