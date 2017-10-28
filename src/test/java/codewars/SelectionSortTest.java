package codewars;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SelectionSortTest {

    @Test
    public void test() {
        int[] arr = {3, 1, 2};
        int[] sorted = sort(arr);
        assertThat(isSorted(sorted), is(equalTo(true)));
        sorted = sort(new int[] { 1, 2, 3});
        assertThat(isSorted(sorted), is(equalTo(true)));
    }

    private int[] sort(int[] arr) {
        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = findMin(i, arr);
            if(minPos != i) {
                exchange(sorted, i, minPos);
            }
        }
        return sorted;
    }

    private int findMin(int i, int[] arr) {
        int min = i;
        for (int j = 0; j < i + 1; j++) {
           if(arr[j] < min) min = arr[j];
        }
        return min;
    }

    private void exchange(int[] sorted, int i, int minPos) {
        int swap = sorted[i];
        sorted[i] = sorted[minPos];
        sorted[minPos] = swap;
    }

    @Test
    public void isSortedWorks() {
        int[] ints = {1, 2, 3};
        assertThat(isSorted(ints), is(equalTo(true)));
        ints = new int[] {2, 1, 3};
        assertThat(isSorted(ints), is(equalTo(false)));
    }

    private boolean isSorted(int[] sorted) {
        for (int i = 1; i < sorted.length; i++) {
            if(sorted[i] < sorted[i - 1]) return false;
        }
        return true;
    }
}
