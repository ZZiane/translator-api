package org.zzach.translator.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.zzach.translator.models.HttpContract;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.stream.Collectors;

public class Fetcher {
    private final ObjectMapper objectMapper;

    public Fetcher() {
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Object> fetch(HttpContract httpContract) {
        HttpMethod method = httpContract.getHttpMethod();
        String url = httpContract.getHttpPath();
        Map<String, String> requestParams = httpContract.getFormData();

        String queryString = requestParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        HttpRequest.Builder builder = HttpRequest.newBuilder();

        if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
            if (!queryString.isEmpty()) {
                url += "?" + queryString;
            }
            builder.uri(URI.create(url));
            builder.method(method.name(), HttpRequest.BodyPublishers.noBody());
        } else {
            try {
                String jsonBody = objectMapper.writeValueAsString(requestParams);
                builder.uri(URI.create(url));
                builder.method(method.name(), HttpRequest.BodyPublishers.ofString(jsonBody));
                builder.header("Content-Type", "application/json");
            } catch (IOException e) {
                throw new RuntimeException("Failed to serialize request body: " + e.getMessage(), e);
            }
        }

        String[] headers = AuthManager.toHeadersArray();

        if (headers.length % 2 != 0) {
            throw new RuntimeException("Invalid headers array length, must be even number");
        }
        if (headers.length >= 2) {
            builder.headers(headers);
        }

        try {
            HttpRequest request = builder.build();
            HttpResponse<String> response = ApiClient.getClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
            } else {
                throw new RuntimeException("Failed to fetch: HTTP " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Fetch error for URL " + url + ": " + e.getMessage(), e);
        }
    }
}
