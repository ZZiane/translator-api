package org.zzach.translator.clients;

import java.util.HashMap;
import java.util.Map;

public class AuthManager {
	
	private static Map<String, String> headers = new HashMap<String, String>(); 
	
	
	public static void hasApiKey(String key) {
		AuthManager.headers.put("X-API-KEY", key);
	}
	
	public static void header(String name, String value) {
		AuthManager.headers.put(name, value);
	}

    public static String[] toHeadersArray() {
        return headers.entrySet()
                      .stream()
                      .flatMap(entry -> java.util.stream.Stream.of(entry.getKey(), entry.getValue()))
                      .toArray(String[]::new);
    }
	
}
