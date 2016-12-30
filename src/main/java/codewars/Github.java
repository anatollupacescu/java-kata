package codewars;

import java.util.Arrays;

public class Github {

    public int solution(int k, int[] a) {
        return binarySearchForLargestSum(a, k);
    }

    /**
     * Computes minimal largest sum as a result of dividing the array into blocks.
     */
    private int binarySearchForLargestSum(int[] a, int maxBlockCount) {
        int lowerBound = max(a);
        int upperBound = sum(a);

        if (maxBlockCount == 1) {
            return upperBound;
        }

        if (maxBlockCount >= a.length) {
            return lowerBound;
        }

        while (lowerBound <= upperBound) {
            int mid = (lowerBound + upperBound) / 2;
            if (canDivideBlocksWithLargestSum(a, maxBlockCount, mid)) {
                upperBound = mid - 1;
            } else {
                lowerBound = mid + 1;
            }
        }

        return lowerBound;
    }

    /**
     * Determines if it's possible to divide the array into blocks such that the
     * given largest sum is attainable.
     * @return {boolean} True if so, false otherwise.
     */
    private boolean canDivideBlocksWithLargestSum(int[] a, int maxBlockCount, double largestSum) {
        int blockSum = 0;
        int blockCount = 0;

        for (int i = 0; i < a.length; i++) {
            int n = a[i];
            if (blockSum + n <= largestSum) {
                blockSum += n;
            } else {
                blockSum = n;
                blockCount++;
            }
            if (blockCount >= maxBlockCount) {
                return false;
            }
        }

        return true;
    }

    /**
     * Computes the sum of elements of an array.
     */
    private int sum(int[] a) {
        return Arrays.stream(a).sum();
    }

    private int max(int[] a) {
        return Arrays.stream(a).max().getAsInt();
    }
}
