import { writable } from "svelte/store";

export enum ToastType {
  SUCCESS = "success",
  ERROR = "error",
  INFO = "info",
}

export interface ToastRequest {
  message: string;
  type: ToastType;
}

export const toastQueue = writable<ToastRequest[]>([]);

export function addToToastQueue(toastRequest: ToastRequest) {
  toastQueue.update((toasts) => [...toasts, toastRequest]);
}
