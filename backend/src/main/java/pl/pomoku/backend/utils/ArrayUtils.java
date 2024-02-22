package pl.pomoku.backend.utils;

public class ArrayUtils {
    public static void swap(Integer[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
