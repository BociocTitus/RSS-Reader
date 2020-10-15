package titus.cyantrial.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "analytics_result")
@Entity(name = "analytics_result")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsResult extends BaseEntity<Long> {
    private String url;
    private String headerTitle;

    @OneToMany
    @JoinColumn(name = "result_id")
    private Set<AnalyticsResultFrequencyEntry> analyticsResultSet;
}
