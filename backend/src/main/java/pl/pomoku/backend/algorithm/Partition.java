package pl.pomoku.backend.algorithm;

import pl.pomoku.backend.utils.ArrayUtils;

public class Partition {
    public static int partition(Integer[] arr) {
        int l = -1, r = 0, n = arr.length;
        while (r < n - 1) {
            if (arr[r] < arr[n - 1]) {
                l++;
                if (r != l) {
                    ArrayUtils.swap(arr, l, r);
                }
            }
            r++;
        }
        ArrayUtils.swap(arr, l + 1, n - 1);
        return l + 1;
    }
}
