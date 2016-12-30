package codewars;

import java.util.Arrays;

public class GroupPermutation {

    public static void main(String[] args) {
        Double d = new Double(3.0);
        int[] arr = {2, 1, 5, 1, 2, 2, 2};
        GroupPermutation gp = new GroupPermutation();
        int result = gp.getMinimumForGroupOf2(arr, 0);
        int expected = 8;
        assertEquals(expected, result);
        assertEquals(7, gp.getMinimumForGroupOf2(arr, 1));
        assertEquals(6, gp.getMinimumForGroupOf2(arr, 2));
        assertEquals(6, gp.compute(arr, 3));
    }

    private int getMinimumForManyGroups(int[] arr, int groups, int start) {
        if (groups == 2) {
            return getMinimumForGroupOf2(arr, start);
        } else {
            groups--;
            int length = arr.length;
            int currentSegmentValue = 0;
            int min = Integer.MAX_VALUE;
            for (int i = start; i < length; i++) {
                int tailValue = getMinimumForManyGroups(arr, groups, i);
                min = Math.min(min, Math.max(currentSegmentValue, tailValue));
                if (i < length) {
                    currentSegmentValue += arr[i];
                }
            }
            return min;
        }
    }

    private int getMinimumForGroupOf2(int[] arr, int start) {
        int length = arr.length;
        int sum = Arrays.stream(arr).skip(start).sum();
        int left = 0;
        int min = Integer.MAX_VALUE;
        for (int i = start; i < length; i++) {
            int right = sum - left;
            min = Math.min(min, Math.max(right, left));
            if (i < length) {
                left += arr[i];
            }
        }
        return min;
    }

    public int compute(int[] A, int K) {
        if (K > A.length) K = A.length;
        if (K <= 1 || A.length == 1) return Arrays.stream(A).sum();
        return getMinimumForManyGroups(A, K, 0);
    }

    private static void assertEquals(int one, int two) {
        if (one != two) {
            System.out.println(String.format("Expected %s, got %s", one, two));
            throw new IllegalStateException("Assertion failed");
        }
        System.out.println("All good");
    }
}
