package codewars;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BalanceIndexTest {

    public int balanceIndex(int[] A) {
        int length = A.length;
        if(length < 2) return  -1;
        long[] longs = new long[length];
        for(int i = 0; i < length; i++) {
            longs[i] = (long)A[i];
        }
        long right = Arrays.stream(longs).sum();
        long left = 0;
        for(int i = 0; i < length; i++) {
            long current = longs[i];
            if(right - current == left) return i;
            left += current;
            right -= current;
        }
        return -1;
    }

    @Test
    public void test6() {
        int[] arr = new int[] { 848484, -1, 1};
        assertEquals(0, balanceIndex(arr));
    }

    @Test
    public void test5() {
        int[] arr = new int[] { -1, 1, 8348383 };
        assertEquals(2, balanceIndex(arr));
    }

    @Test
    public void test4() {
        int[] arr = new int[] { 1, 2, 3};
        assertEquals(-1, balanceIndex(arr));
    }

    @Test
    public void test3() {
        int[] arr = new int[] { };
        assertEquals(-1, balanceIndex(arr));
        assertEquals(-1, balanceIndex(new int[] { 1 }));
    }

    @Test
    public void test1() {
        int[] arr = new int[] { 1, 2, 3, 0, 3, 2, 1};
        assertEquals(3, balanceIndex(arr));
    }

    @Test
    public void test2() {
        int[] arr = new int[] {-1, 3, -4, 5, 1, -6, 2, 1};
        assertEquals(1, balanceIndex(arr));
    }
}
