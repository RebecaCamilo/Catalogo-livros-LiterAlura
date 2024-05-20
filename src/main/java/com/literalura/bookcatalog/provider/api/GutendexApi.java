package com.literalura.bookcatalog.provider.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexApi {

    private static String BASE_URL = "https://gutendex.com/books";

    private static HttpClient createClient() {
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public static HttpRequest createRequestToGetBook(String title) {

        String url = BASE_URL + "?search=" + title.replace(" ", "%20");

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
    }

    public static HttpResponse<String> getResponse(HttpRequest request) {
        HttpClient client = createClient();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Não foi estabelecer conexão com a ExchangerateApi. Finalizando programa...");
            System.exit(0);
        }
        return null;
    }

}
