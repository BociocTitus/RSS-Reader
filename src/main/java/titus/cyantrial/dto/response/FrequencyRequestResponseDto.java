package titus.cyantrial.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FrequencyRequestResponseDto {
    private final String newsHeader;
    private final String originalLink;
    private final List<FrequencyEntryDto> frequencyEntryDtos;
}
