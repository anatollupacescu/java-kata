package codewars;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

public class GroupPermutatorTest {

    private final GroupPermutation permutator = new GroupPermutation();
    private final Github github = new Github();

    private int getMinimalLargeSum(int[] a, int i) {
        return permutator.compute(a, i);
    }

    @Test
    public void simple_test() {
        int[] arr = { 3, 4, 5, 6, 7, 8};
        int size = 4;
        int expected = github.solution(size, arr);
        Assert.assertEquals(expected, permutator.compute(arr, size));
    }

    @Test
    public void small_random_ones() {
        Random r = new Random();
        int[] A = IntStream.range(0, 100).map(i -> r.nextInt(2)).toArray();
        int size = 4;
        int result = getMinimalLargeSum(A, size);
        int expected = github.solution(size, A);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void medium_zeros() {
        int[] A = IntStream.range(0, 1000).map(i -> (i == 7500) ? 99 : 0).toArray();
        int size = 4;
        int res = getMinimalLargeSum(A, size);
        int expected = github.solution(size, A);
        Assert.assertEquals(expected, res);
    }

    @Test
    public void medium_random() {
        Random r = new Random();
        int[] A = IntStream.range(0, 2000).map(i -> 1 + r.nextInt(100)).toArray();
        int size = 4;
        int res = getMinimalLargeSum(A, size);
        int expected = github.solution(size, A);
        Assert.assertEquals(expected, res);
    }

    @Test
    public void testCod_1() {
        int[] arr =  { 42 };
        Assert.assertEquals(42, getMinimalLargeSum(arr, 10000));
    }

    @Test
    public void testGroup2_1() {
        int[] arr = {2, 1, 5, 1, 2, 2, 2};
        int size = 4;
        int expected = github.solution(size, arr);
        Assert.assertEquals(expected, getMinimalLargeSum(arr, size));
    }

    @Test
    public void testGroup2_2() {
        int[] arr = {1, 5, 1, 2, 2, 2};
        int size = 4;
        int expected = github.solution(size, arr);
        Assert.assertEquals(expected, getMinimalLargeSum(arr, size));
    }

    @Test
    public void testGroup2_3() {
        int[] arr = new int[]{0, 1, 2, 3};
        Assert.assertEquals(6, getMinimalLargeSum(arr, 0));
        Assert.assertEquals(6, getMinimalLargeSum(arr, 1));
        Assert.assertEquals(3, getMinimalLargeSum(arr, 3));
    }
}