package titus.cyantrial.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseEntity<IDType> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected IDType id;
}
