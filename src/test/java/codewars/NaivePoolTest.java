package codewars;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongBinaryOperator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class NaivePoolTest {

    private int calc(int N, int offset, int M) {
        return (N + offset) % M;
    }

    public int solution(int N, int M) {
        int offset = 0;
        int numberOfChocolates = 0;
        while(M > N) {
            M -= N;
        }
        int calc = calc(N, offset, M);
        if ( calc == 0) {
            return (N + offset) / M;
        } else while (calc != 0) {
            offset = calc;
            numberOfChocolates += N / M;
            calc = calc(N, offset, M);
        }
        return (N + offset) / M + numberOfChocolates;
    }

    @Test
    public void test4() {
        assertEquals(5, solution(5, 2));
        assertEquals(5, solution(5, 6));
        assertEquals(3, solution(6, 2));
        assertEquals(4, solution(4, 5));
        assertEquals(4, solution(12, 21));
        assertEquals(27, solution(27, 16));
    }

    private int[] shifted(int[] A, int K) {
        int length = A.length;
        if(length < 2 || K < 1) return A;
        if(K > length) K = K % length;
        int[] shifted = new int[length];
        System.arraycopy(A, 0, shifted, K, length - K);
        System.arraycopy(A, length - K, shifted, 0, K);
        return shifted;
    }

    @Test
    public void test3() {
        assertEquals(19 % 8, 3);
    }

    @Test
    public void test2() {
        int[] arr = new int[] {-1, 3, -4, 5, 1, -6, 2, 1};
        int[] arr1 = new int[] { 1, -1, 3, -4, 5, 1, -6, 2};
        int[] arr2 = new int[] { 2, 1, -1, 3, -4, 5, 1, -6};
        assertArrayEquals(arr, shifted(arr, arr.length));
        assertArrayEquals(arr1, shifted(arr, 9));
        assertArrayEquals(arr2, shifted(arr, 10));
        assertArrayEquals(new int[] {}, shifted(new int[] {}, 2));
        assertArrayEquals(new int[] { 1 }, shifted(new int[] {1}, 2));
    }

    private int maxStreak(int n) {
        String input = Integer.toBinaryString(n);
        int currentStreak = 0;
        int maxStreak = 0;
        for(char c : input.toCharArray()) {
            switch (c) {
                case '0':
                    currentStreak ++;
                    break;
                case '1':
                    if(currentStreak > maxStreak) {
                        maxStreak = currentStreak;
                    }
                    currentStreak = 0;
                default:
            }
        }
        return maxStreak;
    }

    @Test
    public void test1() {
        assertEquals(5, maxStreak(1041));
    }

    public int equi ( int A[], int n ) {
        int k, m, lsum, rsum;
        for(k = 0; k < n; ++k) {
            lsum = 0; rsum = 0;
            for(m = 0; m < k; ++m) lsum += A[m];
            for(m = k + 1; m < n; ++m) rsum += A[m];
            if (lsum == rsum) return k;
        }
        return -1;
    }

    public int solution(int[] input) {
        int sum = 0;
        for(int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        int left = 0;
        int right = sum;
        for(int i = 0; i < input.length - 2; i++) {
            left += input[i];
            right -= left;
            right -= input[i + 1];
            if(right == left) return i + 1;
        }
        return -1;
    }

    public int solution1(int[] src) {
        long[] input = new long[src.length];
        for(int i = 0; i < src.length; i++) {
            input[i] = (long)src[i];
        }
        final long sum = Arrays.stream(input).sum();
        final AtomicInteger counter = new AtomicInteger(0);
        final AtomicInteger result = new AtomicInteger(-1);
        LongBinaryOperator lol = (left, currentValue) -> {
            int cursor = counter.getAndIncrement();
            long right = sum - left - currentValue;
            if(right == left) {
                result.set(cursor);
            }
            return left + currentValue;
        };
        Arrays.stream(input).reduce(0, lol);
        return result.get();
    }

    @Test
    public void test() {
        int[] input = new int[] {1082132608, 0, 1082132608};
        assertEquals(1, solution1(input));
    }

    @Test
    public void t1() {
        long left = 1_082_132_608L;
        assertEquals(1082132608, left);
        long right = -2_130_702_080L;
        assertEquals(left * -1, right - left);
    }
}