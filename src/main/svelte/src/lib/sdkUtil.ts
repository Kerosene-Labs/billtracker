import {AuthApi, Configuration, ResponseError} from "$lib/sdk";
// @ts-ignore
import { PUBLIC_API_URL } from '$env/static/public';
import {addToToastQueue, ToastType} from "$lib/toast";
import {goto} from "$app/navigation";

export let apiConfig = new Configuration({
    basePath: PUBLIC_API_URL,
})

export async function getErrorMessageFromSdk(error: ResponseError): Promise<string> {
    if (error.response) {
        // Extract and process the response body
        const errorBody = await error.response.json().catch(() => null); // Handle non-JSON responses
        // Add the error message to the error queue
        return errorBody?.message || "An unexpected error occurred";
    } else {
        // Handle network or other errors
        return error.message || "A network error occurred";
    }
}

export async function validateAndEnforceSession() {
    await new AuthApi(apiConfig).validateSession()
        .then((result) => {
            if (!result.valid) {
                addToToastQueue({message: "Your session was invalid, you've been signed out.", type: ToastType.ERROR});
                goto("/");
            } else {
                console.log("session fine")
            }
        })
        .catch(async (error) => {
            console.log(error)
            await getErrorMessageFromSdk(error)
                .then((msg) => {
                    addToToastQueue({message: msg, type: ToastType.ERROR});
                    goto("/");
                })
        })
        .finally(() => {console.log("finished")})
}