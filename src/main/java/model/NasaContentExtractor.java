package model;

import common.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor {

    public List<Content> extractContent(String json) {
        var parser = new JsonParser();
        List<Map<String, String>> attributeList = parser.parse(json);

        List<Content> contentList = new ArrayList<>();

        for (Map<String, String> attribute : attributeList) {
            var title = attribute.get("title");
            var imageUrl = attribute.get("url");
            var content = new Content(title, imageUrl);

            contentList.add(content);
        }

        return contentList;
    }
}
