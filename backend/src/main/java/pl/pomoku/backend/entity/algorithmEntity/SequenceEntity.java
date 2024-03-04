package pl.pomoku.backend.entity.algorithmEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pomoku.backend.algorithm.stackSequence.SequenceType;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sequence_data")
public class SequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SequenceType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child_sequence_id")
    private SequenceEntity sequence;
    private Integer number;
}
