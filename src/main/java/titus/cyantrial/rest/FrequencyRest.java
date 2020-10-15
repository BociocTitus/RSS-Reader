package titus.cyantrial.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;
import titus.cyantrial.domain.AnalyticsResult;
import titus.cyantrial.domain.AnalyticsResultFrequencyEntry;
import titus.cyantrial.dto.response.AnalyzeRequestResponseDto;
import titus.cyantrial.dto.response.FrequencyEntryDto;
import titus.cyantrial.dto.response.FrequencyRequestResponseDto;
import titus.cyantrial.service.FrequencyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("frequency")
public class FrequencyRest {

    private final FrequencyService frequencyService;

    @GetMapping("{id}")
    public FrequencyRequestResponseDto analyzeNewUrls(@PathVariable final Long id) {
        final Pair<AnalyticsResult, List<AnalyticsResultFrequencyEntry>> topOccurrencesForAnalysis = frequencyService.getTopOccurencesForNews(id);
        final List<FrequencyEntryDto> frequencyEntryDtos = topOccurrencesForAnalysis.getSecond()
                .stream()
                .map(FrequencyEntryDto::new)
                .collect(Collectors.toList());
        final AnalyticsResult analyticsResult = topOccurrencesForAnalysis.getFirst();

        return FrequencyRequestResponseDto.builder()
                .newsHeader(analyticsResult.getHeaderTitle())
                .originalLink(analyticsResult.getUrl())
                .frequencyEntryDtos(frequencyEntryDtos)
                .build();
    }

}
