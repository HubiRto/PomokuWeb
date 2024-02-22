package pl.pomoku.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class HoarePartitionResponse {
    private List<HoareOperation> operations;
    private int result;
    private int partitionCount;
    private int recursionDepth;
}
