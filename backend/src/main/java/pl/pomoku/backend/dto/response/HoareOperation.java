package pl.pomoku.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pl.pomoku.backend.entity.HoareOperationEntity;

import java.util.Arrays;

@AllArgsConstructor
@ToString
@Data
public class HoareOperation {
    private int arg;
    private Integer[] arr;
    private int index;

    public HoareOperationEntity toEntity() {
        return new HoareOperationEntity(arg, Arrays.asList(arr), index);
    }
}