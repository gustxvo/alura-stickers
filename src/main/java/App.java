import common.HttpClient;
import io.github.cdimascio.dotenv.Dotenv;
import common.JsonParser;
import model.Content;
import model.ImdbContentExtractor;
import model.NasaContentExtractor;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {
        ImdbContentExtractor extractor = new ImdbContentExtractor();
        final String url = Dotenv.load().get("IMDB_TOP_MOVIES_API_ACCESS_URL");
//        NasaContentExtractor extractor = new NasaContentExtractor();
//        final String url = Dotenv.load().get("NASA_APOD_API_ACCESS_URL");

       var httpClient = new HttpClient();
       String json = httpClient.getHttpData(url);

        // Show data
        var stickersGenerator = new StickersGenerator();
        List<Content> contentList = extractor.extractContent(json);

        for (int i = 0; i < 3; i++) {
            Content content = contentList.get(i);

            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            var fileName = content.getTitle() + ".png";
            stickersGenerator.create(inputStream, fileName);

            System.out.println("Title: \u001b[1m" + content.getTitle() + "\u001b[m");
            System.out.println("Image: \u001b[1m" + content.getImageUrl() + "\u001b[m\n");
        }

        // Deserialize Json with Jackson
        /*try {
            ObjectMapper objectMapper = new ObjectMapper();
            model.Items items = objectMapper.readValue(response, model.Items.class);
            model.Movie[] movies = items.movies;

            for (model.Movie movie : movies) {
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
