package org.zzach.translator.clients;

import java.net.http.HttpClient;

public class ApiClient {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public static HttpClient getClient() {
        return CLIENT;
    }
}
