package io.kangov.stix.util;

import java.net.URI;
import java.net.http.*;

public class Util{

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        String path = "https://raw.githubusercontent.com/mitre-attack/attack-stix-data/master/enterprise-attack/enterprise-attack-1.0.json";
        var json = get(path);
        System.out.println(json);
    }

    public static String get(String path) throws Exception {
        var request = HttpRequest.newBuilder().uri(URI.create(path)).GET().build();
        var httpResponse = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofInputStream());
        try (var body = httpResponse.body()) {
            return new String(body.readAllBytes());
        }
    }

}
