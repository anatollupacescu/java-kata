package codewars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetsOfArrayTest {

    public int solution(int[] A) {
        int length = 0;
        for (int i = 0; i < A.length; i++) {
            int setLength = getSetLength(A, i);
            if (setLength > length) {
                length = setLength;
            }
        }
        return length;
    }

    private int getSetLength(int[] arr, int position) {
        int initial = arr[position];
        int element = initial;
        int lenght = 1;
        while (arr[element] != initial) {
            System.out.println(element);
            element = arr[element];
            lenght++;
        }
        System.out.println(element);
        return lenght;
    }

    @Test
    public void test6() {
        int[] A = new int[7];
        A[0] = 5;
        A[1] = 4;
        A[2] = 0;
        A[3] = 3;
        A[4] = 1;
        A[5] = 6;
        A[6] = 2;
        assertEquals(4, solution(A));
    }

}
