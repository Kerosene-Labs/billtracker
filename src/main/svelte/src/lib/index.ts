import {writable} from "svelte/store";

export const errorQueue = writable<string[]>([]);