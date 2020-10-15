package titus.cyantrial.service.analytics;

import titus.cyantrial.dto.external.RSSItem;

import java.util.List;

public interface IRssItemAnalysisService {
    List<String> analyzeItem(final RSSItem rssItem);
}
