import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Make an HTTP request to get the top 250 movies
        // imDB api system is down
//        final String apiAccessToken = Dotenv.load().get("API_ACCESS_TOKEN");
//        String url = "https://imdb-api.com/en/API/MostPopularMovies/" + apiAccessToken;

        // alternative url provided by an Alura instructor, Jacqueline Oliveira
        final String url = Dotenv.load().get("API_ACCESS_URL");

        var client = HttpClient.newHttpClient();
        var uri = URI.create(url);
        var request = HttpRequest.newBuilder(uri).GET().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        // Show data
        List<Map<String, String>> moviesList = new JsonParser().parse(response);
       /* for (Map<String, String> movie : moviesList) {
            var rating = Float.parseFloat(movie.get("imDbRating"));
            System.out.println("Title: \u001b[1m" + movie.get("title") + "\u001b[m");
            System.out.println("Image: \u001b[1m" + movie.get("image") + "\u001b[m");
            System.out.println("\u001b[37;1m\u001b[45mRating: " + rating +"  \u001b[m");
            for (int i = 0; i < Math.round(rating); i++) {
                System.out.print("\u2B50");
            }
            System.out.println("\n");
        }*/

        // Deserialize Json with Jackson
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Items items = objectMapper.readValue(response, Items.class);
            Movie[] movies = items.movies;

            for (Movie movie : movies) {
                var rating = Float.parseFloat(movie.getImDbRating());
                System.out.println("Title: \u001b[1m" + movie.getTitle() + "\u001b[m");
                System.out.println("Image: \u001b[1m" + movie.getImage() + "\u001b[m");
                System.out.println("\u001b[37;1m\u001b[45mRating: " + rating +"  \u001b[m");
                for (int i = 0; i < Math.round(rating); i++) {
                    System.out.print("\u2B50");
                }
                System.out.println("\n");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
