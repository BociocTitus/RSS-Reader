package titus.cyantrial.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AnalyzeRequestResponseDto {
    private List<Long> resultIds;
}
