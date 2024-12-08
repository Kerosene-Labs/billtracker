package com.kerosenelabs.billtracker.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerosenelabs.billtracker.exception.AuthException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class SupabaseClientService {
    private String serviceRoleKey;
    private String instanceUrl;

    public SupabaseClientService(@Value("${supabase.serviceRole.key}") String serviceRoleKey,
            @Value("${supabase.instanceUrl}") String instanceUrl) {
        this.instanceUrl = instanceUrl;
        this.serviceRoleKey = serviceRoleKey;
    }

    /**
     * Configure an OkHttp {@link Request}.
     */
    public Request.Builder getBuilder(String endpoint) {
        return new Request.Builder()
                .url(instanceUrl + endpoint)
                .header("apikey", serviceRoleKey);
    }

    /**
     * Configure an OkHttp {@link Request}.
     */
    public Request.Builder getBuilder(String endpoint, String bearerToken) {
        return new Request.Builder()
                .url(instanceUrl + endpoint)
                .header("apikey", serviceRoleKey)
                .header("Authorization", "Bearer " + serviceRoleKey);
    }

    /**
     * Send a {@code POST} request to
     * 
     * @throws IOException
     * @throws AuthException
     */
    public <RequestType, ResponseType> ResponseType post(String endpoint, RequestType request,
            Class<ResponseType> responseTypeClass)
            throws IOException, AuthException {
        // prerequisites
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OkHttpClient httpClient = new OkHttpClient();

        // build our request
        Request networkRequest = getBuilder(endpoint)
                .post(RequestBody.create(objectMapper.writeValueAsBytes(request)))
                .build();
        Response response = httpClient.newCall(networkRequest).execute();

        // map our response
        if (response.isSuccessful()) {
            return objectMapper.readValue(response.body().bytes(), responseTypeClass);
        }
        throw new AuthException(String.format("Got %s\n---\n%s", response.code(), response.body().string()));
    }

    /**
     * Send a {@code POST} request to
     * 
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */
    public <RequestType, ResponseType> ResponseType post(String endpoint, Optional<String> bearerToken,
            RequestType request, Class<ResponseType> responseTypeClass)
            throws JsonMappingException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue("{}", responseTypeClass);
    }
}
