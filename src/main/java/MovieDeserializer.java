import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class MovieDeserializer extends StdDeserializer<Movie> {
    public MovieDeserializer() {
        this(null);
    }

    protected MovieDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext dsCtx)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String title = node.get("title").textValue();
        String image = node.get("image").toString();
        String imDbRating = node.get("imDbRating").asText();


        return new Movie(title, image, imDbRating);
    }
}
