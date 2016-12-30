package codewars;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class GithubTest {

    private final Github permutator = new Github();

    private int getMinimalLargeSum(int[] a, int i) {
        return permutator.solution(i, a);
    }

    @Test
    public void testGroup3_1() {
        int[] arr = {2, 1, 5, 1, 2, 2, 2};
        Assert.assertEquals(6, getMinimalLargeSum(arr, 3));
    }

    @Test
    public void small_random_ones() {
        Random r = new Random();
        int[] A = IntStream.range(0, 100).map(i -> r.nextInt(2)).toArray();
        double min = getMinimalLargeSum(A, 4);
        Assert.assertTrue(min>0);
    }

    @Test
    public void medium_zeros() {
        long start = System.currentTimeMillis();
        int[] A = IntStream.range(0, 15000).map(i -> (i == 7500) ? 99 : 0).toArray();
        double res = getMinimalLargeSum(A, 4);
        Assert.assertFalse(res == -1);
        Assert.assertTrue(System.currentTimeMillis() - start < 1000);
    }

    @Test
    public void medium_random() {
        Random r = new Random();
        int[] A = IntStream.range(0, 20_000).map(i -> 1 + r.nextInt(100)).toArray();
        long start = System.currentTimeMillis();
        double res = getMinimalLargeSum(A, 4);
        Assert.assertFalse(res == -1);
        Assert.assertTrue(System.currentTimeMillis() - start < 1000);
    }

    @Test
    public void testCod_1() {
        int[] arr =  { 42 };
        Assert.assertEquals(42, getMinimalLargeSum(arr, 10000));
    }

    @Test
    public void testGroup2_1() {
        int[] arr = {2, 1, 5, 1, 2, 2, 2};
        Assert.assertEquals(8, getMinimalLargeSum(arr, 2));
    }

    @Test
    public void testGroup2_2() {
        int[] arr = {1, 5, 1, 2, 2, 2};
        Assert.assertEquals(7, getMinimalLargeSum(arr, 2));
    }

    @Test
    public void testGroup2_3() {
        int[] arr = new int[]{0, 1, 2, 3};
        assertEquals(6, getMinimalLargeSum(arr, 1));
        Assert.assertEquals(3, getMinimalLargeSum(arr, 3));
    }
}
