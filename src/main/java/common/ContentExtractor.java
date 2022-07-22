package common;

import model.Content;

import java.util.List;

public interface ContentExtractor {

    List<Content> extractContent(String json);
}
