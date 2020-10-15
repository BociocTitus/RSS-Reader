package titus.cyantrial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import titus.cyantrial.domain.AnalyticsResult;

@Repository
public interface AnalyticsResultRepository extends JpaRepository<AnalyticsResult, Long> {
}
