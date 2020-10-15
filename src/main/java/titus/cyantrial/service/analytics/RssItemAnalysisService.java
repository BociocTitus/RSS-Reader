package titus.cyantrial.service.analytics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import titus.cyantrial.dto.external.RSSItem;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RssItemAnalysisService implements IRssItemAnalysisService {
    private List<String> stopwords;

    @Value("${cyan-trial.stopwords-filepath}")
    private String stopwordsFilePath;

    private final String descriptionContentRegExp = "<a\\b[^>]*>(.*?)<\\/a";

    @PostConstruct
    private void readFilesAfterConstruct() {
        try {
            File resource = new ClassPathResource(
                    stopwordsFilePath).getFile();
            String stopwordsString = new String(
                    Files.readAllBytes(resource.toPath()));
            stopwords = Arrays.asList(stopwordsString.replaceAll("\r", "").split("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            stopwords = List.of();
        }
    }

    private List<String> removeStopWords(final String description) {
        final List<String> splitString = Stream.of(description.split(" "))
                .collect(Collectors.toList());
        splitString.removeAll(stopwords);

        return splitString;
    }

    public List<String> analyzeItem(final RSSItem rssItem) {
        final String descriptionWithContent = extractContentFromDescription(rssItem.getDescription());
        return removeStopWords(descriptionWithContent);
    }

    private String extractContentFromDescription(final String description) {
        Pattern pattern = Pattern.compile(descriptionContentRegExp, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(description);
        List<String> result = new ArrayList<>();

        while(matcher.find()) {
            result.add(matcher.group(1));
        }

        return String.join(" ", result);
    }
}