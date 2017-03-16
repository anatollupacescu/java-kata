package codewars;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MedianArrayTest {

    public int solution(int[] A) {
        int[] sortedArray = java.util.Arrays.stream(A).sorted().toArray();
        int length = sortedArray.length;
        int position = length % 2 == 0 ? length / 2 : (length - 1)/ 2;
        return sortedArray[position];
    }

    @Test
    public void test6() {
        int[] arr = new int[] { 1, 2, 5, 10, 20, 1};
        assertEquals(5, solution(arr));
    }

}
