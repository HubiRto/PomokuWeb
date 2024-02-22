package pl.pomoku.backend.algorithm;

import lombok.AllArgsConstructor;
import pl.pomoku.backend.dto.response.HoareOperation;
import pl.pomoku.backend.entity.HoareOperationEntity;
import pl.pomoku.backend.entity.HoarePartitionInput;
import pl.pomoku.backend.entity.HoarePartitionResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class HoarePartition {
    private Integer[] arr;
    private int k;
    private String hashToken;
    private static int argCount = 0;
    private static int partitionCount = 0;
    private static int recursionDepth = 0;
    private static final List<HoareOperation> operations = new ArrayList<>();

    public static int hoarePartition(Integer[] arr, int k) {
        System.out.println(Arrays.toString(arr));
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        int tmp;
        operations.add(new HoareOperation(++argCount, arr, k));
        int m = Partition.partition(arr);
        partitionCount++;
        int n = arr.length;
        if (n - m == k) {
            return m;
        } else {
            if (n - m > k) {
                Integer[] newArr = Arrays.copyOfRange(arr, m + 1, n - 1);
                recursionDepth++;
                tmp =  m + 1 + hoarePartition(newArr, k);
            } else {
                Integer[] newArr = Arrays.copyOfRange(arr, 0, m - 1);
                recursionDepth++;
                tmp =  hoarePartition(newArr, k - (n - m));
            }
        }
        return tmp;
    }

    public HoarePartitionResult run() {
        int result = hoarePartition(arr, k);
        return HoarePartitionResult.builder()
                .hashToken(hashToken)
                .inputData(new HoarePartitionInput(Arrays.asList(arr), k))
                .result(result)
                .partitionCount(partitionCount)
                .recursionDepth(recursionDepth)
                .operations(toEntities())
                .build();
    }

    private List<HoareOperationEntity> toEntities() {
        return operations.stream().map(HoareOperation::toEntity).collect(Collectors.toList());
    }

}
