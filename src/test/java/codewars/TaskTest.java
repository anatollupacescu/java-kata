package codewars;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3};
        assertEquals(0, iterate(arr, 0));
    }

    private int iterate(int[] arr, int pivot) {
        int max = 0;
        int index = pivot;
        if (index == arr.length) return -1;
        while (true) {
            max += arr[index++];
            System.out.println(String.format("max=%s, future=%s", max, iterate(arr, index)));
        }
    }

    @Test
    public void testSum() {
        int[] arr = new int[]{};
        assertEquals(0, sum(arr, 0, 0));
        arr = new int[]{1};
        assertEquals(1, sum(arr, 0, 1));
        arr = new int[]{1, 2};
        assertEquals(3, sum(arr, 0, 2));
        arr = new int[]{1, 2, 3};
        assertEquals(5, sum(arr, 1, 2));
        arr = new int[]{1, 2, 3, 4};
        assertEquals(3, sum(arr, 0, 2));
        arr = new int[]{1, 2, 3, 4};
        assertEquals(5, sum(arr, 1, 2));
        arr = new int[]{1, 2, 3, 4};
        assertEquals(10, sum(arr, 0, 4));
    }

    private int sum(int[] array, int start, int count) {
        int end = start + count;
        return IntStream.range(start, end).map(index -> array[index]).sum();
    }
}
