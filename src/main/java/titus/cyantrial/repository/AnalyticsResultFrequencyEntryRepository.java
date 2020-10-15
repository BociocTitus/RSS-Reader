package titus.cyantrial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import titus.cyantrial.domain.AnalyticsResultFrequencyEntry;

import java.util.List;

@Repository
public interface AnalyticsResultFrequencyEntryRepository extends JpaRepository<AnalyticsResultFrequencyEntry, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM analytics_result_frequency_entry WHERE result_id = :resultId ORDER BY frequency DESC LIMIT 3")
    List<AnalyticsResultFrequencyEntry> getTopTopicsForResult(final Long resultId);
}
