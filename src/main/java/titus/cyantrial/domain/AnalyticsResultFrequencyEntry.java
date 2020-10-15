package titus.cyantrial.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "analytics_result_frequency_entry")
@Entity(name = "analytics_result_frequency_entry")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AnalyticsResultFrequencyEntry extends BaseEntity<Long> {
    private String topic;
    private Long frequency;
}
