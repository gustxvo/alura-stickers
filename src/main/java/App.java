import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Make an HTTP request to get the top 250 movies

        final String url = Dotenv.load().get("API_ACCESS_URL");

        var client = HttpClient.newHttpClient();
        var uri = URI.create(url);
        var request = HttpRequest.newBuilder(uri).GET().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        List<Map<String, String>> moviesList = new JsonParser().parse(response);

        // Show data
        for (Map<String, String> movie : moviesList) {
            System.out.printf("{\n\t\"title\": \"%s\",\n", movie.get("title"));
            System.out.printf("\t\"image\": \"%s\",\n", movie.get("image"));
            System.out.printf("\t\"imdbRating\": \"%s\"\n},\n", movie.get("imDbRating"));
        }
    }
}
