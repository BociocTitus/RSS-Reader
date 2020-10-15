package titus.cyantrial.dto.response;

import lombok.Getter;
import titus.cyantrial.domain.AnalyticsResultFrequencyEntry;

@Getter
public class FrequencyEntryDto {
    private final Long frequency;
    private final String topic;

    public FrequencyEntryDto(final AnalyticsResultFrequencyEntry analyticsResultFrequencyEntry) {
        frequency = analyticsResultFrequencyEntry.getFrequency();
        topic = analyticsResultFrequencyEntry.getTopic();
    }
}
