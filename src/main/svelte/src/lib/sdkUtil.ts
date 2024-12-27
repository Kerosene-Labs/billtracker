import { Configuration, ResponseError } from "$lib/sdk";
import { goto } from "$app/navigation";
import { addToToastQueue, ToastType } from "$lib/toast";

export function getPublicApiConfig(): Configuration {
  return new Configuration({
    basePath: import.meta.env.VITE_API_URL,
  });
}

export function getPrivateApiConfig(): Configuration {
  const jwt = sessionStorage.getItem("jwt");
  if (!jwt) {
    addToToastQueue({
      message: "We couldn't find any credentials for you, please sign in.",
      type: ToastType.INFO,
    });
    goto("/");
    throw new Error("JWT not set");
  }
  return new Configuration({
    basePath: import.meta.env.VITE_API_URL,
    headers: {
      Authorization: "Bearer " + jwt,
    },
  });
}

export async function getErrorMessageFromSdk(
  error: ResponseError,
): Promise<string> {
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

export function doLogOut() {
  addToToastQueue({ message: "You've been signed out.", type: ToastType.INFO });
  sessionStorage.removeItem("jwt");
  goto("/");
}
