import {Configuration, ResponseError} from "$lib/sdk";
// @ts-ignore
import { PUBLIC_API_URL } from '$env/static/public';
import {addToToastQueue, ToastType} from "$lib/toast";
import {goto} from "$app/navigation";

export function getApiConfig(): Configuration {
    const jwt = sessionStorage.getItem("jwt");
    if (!jwt) {
        throw new Error("JWT not set, invalid session")
    }
    return new Configuration({
        basePath: PUBLIC_API_URL,
        headers: {
            "Authorization": "Bearer " + jwt
        }
    })
}

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