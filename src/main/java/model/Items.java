package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import common.MovieDeserializer;

@JsonIgnoreProperties("errorMessage")
public class Items {

    @JsonProperty("items")
    @JsonDeserialize(contentUsing = MovieDeserializer.class)
    public Movie[] movies;
}
