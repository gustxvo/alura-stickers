import io.github.cdimascio.dotenv.Dotenv;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        final String url = Dotenv.load().get("IMDB_TOP_MOVIES_ACCESS_URL");

        var client = HttpClient.newHttpClient();
        var uri = URI.create(url);
        var request = HttpRequest.newBuilder(uri).GET().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        // Show data
        List<Map<String, String>> moviesList = new JsonParser().parse(response);
        var stickersGenerator = new StickersGenerator();

        for (int i = 0; i < 10; i++) {
            Map<String, String> movie = moviesList.get(i);

            var title = movie.get("title");
            var fileName = title + ".png";
            var imageUrl = movie.get("image").replaceAll("(@+)(.*).jpg$", "$1.png");

            InputStream inputStream = new URL(imageUrl).openStream();
            stickersGenerator.create(inputStream, fileName);

            System.out.println("Title: \u001b[1m" + title + "\u001b[m");
            System.out.println("Image: \u001b[1m" + imageUrl + "\u001b[m\n");
        }

        // Deserialize Json with Jackson
        /*try {
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
        }*/

    }
}
