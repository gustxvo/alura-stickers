import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties("errorMessage")
public class Items {

    @JsonProperty("items")
    @JsonDeserialize(contentUsing = MovieDeserializer.class)
    public Movie[] movies;
}
