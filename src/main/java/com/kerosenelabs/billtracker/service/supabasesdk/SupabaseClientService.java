package com.kerosenelabs.billtracker.service.supabasesdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kerosenelabs.billtracker.exception.AuthException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class SupabaseClientService {
    private String serviceRoleKey;
    private String instanceId;

    public SupabaseClientService(@Value("${supabase.serviceRole.key}") String serviceRoleKey,
            @Value("${supabase.instanceId}") String instanceId) {
        this.instanceId = instanceId;
        this.serviceRoleKey = serviceRoleKey;
    }

    private HttpUrl.Builder getHttpUrlBuilder(String endpoint, Optional<HashMap<String, String>> queryParameters) {
        HttpUrl.Builder httpUrlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host(instanceId + ".supabase.co")
                .addPathSegments(endpoint);
        if (queryParameters.isPresent()) {
            queryParameters.get().entrySet().stream()
                    .forEach(value -> httpUrlBuilder.addQueryParameter(value.getKey(), value.getValue()));
        }
        return httpUrlBuilder;
    }

    /**
     * Configure an OkHttp {@link Request}.
     */
    private Request.Builder getRequestBuilder(HttpUrl httpUrl) {
        return new Request.Builder()
                .url(httpUrl)
                .header("apikey", serviceRoleKey);
    }

    /**
     * Configure an OkHttp {@link Request}.
     */
    public Request.Builder getRequestBuilder(HttpUrl httpUrl, String bearerToken) {
        return new Request.Builder()
                .url(httpUrl)
                .header("apikey", serviceRoleKey)
                .header("Authorization", "Bearer " + serviceRoleKey);
    }

    /**
     * Send a {@code POST} request to
     * 
     * @throws IOException
     * @throws AuthException
     */
    public <RequestType, ResponseType> ResponseType post(String endpoint,
            Optional<HashMap<String, String>> queryParameters,
            RequestType request, Class<ResponseType> responseTypeClass)
            throws IOException, AuthException {
        // prerequisites
        JsonMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OkHttpClient httpClient = new OkHttpClient();

        // build our request
        Request networkRequest = getRequestBuilder(getHttpUrlBuilder(endpoint, queryParameters).build())
                .post(RequestBody.create(mapper.writeValueAsBytes(request)))
                .build();
        Response response = httpClient.newCall(networkRequest).execute();

        // map our response
        if (response.isSuccessful()) {
            String resp = response.body().string();
            System.out.println(resp);
            return mapper.readValue(resp, responseTypeClass);
        }
        throw new AuthException(String.format("Got %s\n---\n%s", response.code(), response.body().string()));
    }

    /**
     * Send a {@code POST} request to
     * 
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public <RequestType, ResponseType> ResponseType post(String endpoint,
            Optional<HashMap<String, String>> queryParameters, Optional<String> bearerToken,
            RequestType request, Class<ResponseType> responseTypeClass)
            throws JsonMappingException, JsonProcessingException {
        throw new RuntimeException("Not implemented.");
        // ObjectMapper objectMapper = new ObjectMapper();
        // return objectMapper.readValue("{}", responseTypeClass);
    }
}
