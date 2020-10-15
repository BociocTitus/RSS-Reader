package titus.cyantrial.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class AnalyseUrlsRequestDto {
    private List<String> urls;
}
