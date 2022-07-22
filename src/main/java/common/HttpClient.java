package common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {

    public String getHttpData(String url) {

        try {
            var client = java.net.http.HttpClient.newHttpClient();
            var uri = URI.create(url);
            var request = HttpRequest.newBuilder(uri).GET().build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
