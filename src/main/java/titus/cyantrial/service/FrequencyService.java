package titus.cyantrial.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import titus.cyantrial.domain.AnalyticsResult;
import titus.cyantrial.domain.AnalyticsResultFrequencyEntry;
import titus.cyantrial.repository.AnalyticsResultFrequencyEntryRepository;
import titus.cyantrial.repository.AnalyticsResultRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FrequencyService {
    private final AnalyticsResultRepository analyticsResultRepository;
    private final AnalyticsResultFrequencyEntryRepository analyticsResultFrequencyEntryRepository;

    public Pair<AnalyticsResult, List<AnalyticsResultFrequencyEntry>> getTopOccurencesForNews(final Long id) {
        final AnalyticsResult analyticsResult = analyticsResultRepository.getOne(id);
        return Pair.of(analyticsResult, analyticsResultFrequencyEntryRepository.getTopTopicsForResult(id));
    }
}
