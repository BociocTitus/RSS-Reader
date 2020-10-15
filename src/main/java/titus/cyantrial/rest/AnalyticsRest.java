package titus.cyantrial.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import titus.cyantrial.dto.request.AnalyseUrlsRequestDto;
import titus.cyantrial.dto.response.AnalyzeRequestResponseDto;
import titus.cyantrial.service.AnalysisService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("analyse")
public class AnalyticsRest {

    private final AnalysisService analysisService;

    @PostMapping("/new")
    public AnalyzeRequestResponseDto analyzeNewUrls(@RequestBody final AnalyseUrlsRequestDto analyseUrlsRequestDto) {

        final List<Long> resultIds = analysisService.analyseUrls(analyseUrlsRequestDto.getUrls());
        return AnalyzeRequestResponseDto.builder()
                .resultIds(resultIds)
                .build();
    }

}
