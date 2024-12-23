import {Configuration, ResponseError} from "$lib/sdk";

export function getPublicApiConfig(): Configuration {
    return new Configuration({
        basePath: import.meta.env.VITE_GOOGLE_OAUTH_URI
    })
}

export function getPrivateApiConfig(): Configuration {
    const jwt = sessionStorage.getItem("jwt");
    if (!jwt) {
        throw new Error("JWT not set, invalid session")
    }
    return new Configuration({
        basePath: import.meta.env.VITE_GOOGLE_OAUTH_URI,
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