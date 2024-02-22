package pl.pomoku.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pomoku.backend.dto.response.HoareOperation;
import pl.pomoku.backend.dto.response.HoarePartitionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoarePartitionResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String hashToken;

    @OneToOne(cascade = CascadeType.ALL)
    private HoarePartitionInput inputData;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hoare_partition_result_id")
    private List<HoareOperationEntity> operations = new ArrayList<>();

    private int result;

    private int partitionCount;

    private int recursionDepth;

    public HoarePartitionResponse getResponse() {
        return new HoarePartitionResponse(getOperations(), result, partitionCount, recursionDepth);
    }

    public List<HoareOperation> getOperations() {
        return this.operations.stream().map(HoareOperationEntity::to).collect(Collectors.toList());
    }
}
