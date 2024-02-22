package pl.pomoku.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {10,0,15,5,3,1,18,12,11,17,7};
        List<Integer> ar = Arrays.asList(arr);

//        6E4022915EB1BB4832C39652933E0DEB
//        6E4022915EB1BB4832C39652933E0DEB

        System.out.println(HashUtils.generateHash(ar, 1).equals(HashUtils.generateHash(ar, 1)));

//        Integer[] arr = {3,12,10,0,6,14,19,16,11,1,9};
//        Integer[] arr = {11,9,16,18,6,4,0,5,2,8,1};
//        new HoarePartition(arr, 6).run();
    }

    @AllArgsConstructor
    @Data
    static class Test {
        private int a;
        private Integer[] arr;
    }
}
