package pl.pomoku.backend.entity.algorithmEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StackSequenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash;
    private int endSize;
    private int maxSize;
    private int top;
    @ElementCollection
    @Column(name = "init_value")
    private List<Integer> initArray;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sequence_id")
    private List<SequenceEntity> sequence;
}
