package titus.cyantrial.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import titus.cyantrial.domain.AnalyticsResult;
import titus.cyantrial.domain.AnalyticsResultFrequencyEntry;
import titus.cyantrial.dto.external.RSSItem;
import titus.cyantrial.dto.external.RSSWrapper;
import titus.cyantrial.repository.AnalyticsResultFrequencyEntryRepository;
import titus.cyantrial.repository.AnalyticsResultRepository;
import titus.cyantrial.service.analytics.RssItemAnalysisService;
import titus.cyantrial.service.external.IHttpService;
import titus.cyantrial.service.external.IRSSReader;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnalysisService {
    private final AnalyticsResultRepository analyticsResultRepository;
    private final AnalyticsResultFrequencyEntryRepository analyticsResultFrequencyEntryRepository;
    private final RssItemAnalysisService rssItemAnalysisService;
    private final IHttpService httpService;
    private final IRSSReader rssReader;

    public List<Long> analyseUrls(final List<String> urls) {
        return urls.stream()
                .map(this::mapUrlToAnalyticsResult)
                .collect(Collectors.toList());
    }

    private Long mapUrlToAnalyticsResult(final String url) {
        final InputStream inputStream = httpService.getUrlContent(url);
        if(inputStream != null) {
            final RSSWrapper rssWrapper = rssReader.readFromInputStream(inputStream);
            final List<AnalyticsResultFrequencyEntry> analyticsResultFrequencyEntries = createAnalyticsResultFrequencies(rssWrapper.getRssChannel().getItems());
            final AnalyticsResult analyticsResult = AnalyticsResult.builder()
                    .headerTitle(rssWrapper.getRssChannel().getTitle())
                    .url(url)
                    .analyticsResultSet(new HashSet<>(analyticsResultFrequencyEntries))
                    .build();
            return analyticsResultRepository.save(analyticsResult).getId();
        }

        return -1L;
    }

    private List<AnalyticsResultFrequencyEntry> createAnalyticsResultFrequencies(List<RSSItem> rssItems) {
        final Map<String, Long> analyticsOccurences = rssItems
                .stream()
                .flatMap(rssItem -> rssItemAnalysisService.analyzeItem(rssItem).stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final List<AnalyticsResultFrequencyEntry> frequencyEntries = analyticsOccurences.entrySet()
                .stream()
                .map(this::createFrequencyFromMapEntry)
                .collect(Collectors.toList());

        return analyticsResultFrequencyEntryRepository.saveAll(frequencyEntries);
    }

    private AnalyticsResultFrequencyEntry createFrequencyFromMapEntry(final Map.Entry<String, Long> entry) {
        return AnalyticsResultFrequencyEntry.builder()
                .topic(entry.getKey())
                .frequency(entry.getValue())
                .build();
    }
}