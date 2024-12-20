import {writable} from "svelte/store";
import {beforeNavigate} from "$app/navigation";
import {validateAndEnforceSession} from "$lib/sdkUtil";

export const prerender = true;