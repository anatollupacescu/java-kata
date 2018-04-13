package codewars;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class PlainTest {

    private List<String> permutate(char[] input, List<String> output, String acc) {
        int length = input.length;
        if(length == 2) {
            output.add(acc + String.valueOf(input));
            output.add(acc + String.valueOf(swap(input)));
        } else {
            for (int i = 0; i < input.length; i++) {
                String head = acc + String.valueOf(input[i]);
                permutate(copySkip(input, i), output, head);
            }
        }
        return output;
    }

    private char[] swap(char[] input) {
        return new char[] { input[1], input[0]};
    }

    private char[] copySkip(char[] input, int skip) {
        int length = input.length;
        char[] output = new char[length - 1];
        int index = 0;
        for (int c = 0; c < length; c++) {
            if(c != skip) output[index++] = input[c];
        }
        return output;
    }

    @Test
    public void testCopySkip() {
        char[] input = new char[] { 'a', 'b', 'c', 'd' };
        Assert.assertEquals("abd", String.valueOf(copySkip(input, 2)));
        Assert.assertEquals("abc", String.valueOf(copySkip(input, 3)));
    }

    @Test
    public void permTest() {
        List<String> output = new ArrayList<>();
        char[] input = new char[] { 'a', 'b'};
        List<String> res = permutate(input, output, "");
        List<String> words = Arrays.asList("ab", "ba");
        Iterator<String> iterator = res.iterator();
        for (Iterator<String> expected = words.iterator(); expected.hasNext(); ) {
            String next =  expected.next();
            Assert.assertEquals(iterator.next(), next);
        }

        input = new char[] { 'a', 'b', 'c'};
        res = permutate(input, new ArrayList<>(), "");
        words = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
        iterator = res.iterator();
        for (String s : words) {
            Assert.assertEquals(s, iterator.next());
        }
    }

    public static int t() {
        return -1;
    }

    interface FI {
        Object create();
    }

    @Test
    public void canI() {
        FI t = String::new;
        Object res = met(t);
        Assert.assertEquals("", res);
        res = met(PlainTest::t);
        Assert.assertEquals(-1, res);
    }

    private Object met(FI t) {
       return t.create();
    }

    @Test
    public void test() {
        Assert.assertEquals(2_147_483_647, Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE * 2L);
        int allElements = 100_000 * 10_000;
        Assert.assertEquals(1_000_000_000, allElements);
        Assert.assertFalse(allElements > Integer.MAX_VALUE);
        int L = Integer.MAX_VALUE;
        long maxPlusOne = (long)L + 1;
        Assert.assertEquals(2_147_483_648L, maxPlusOne);
    }
}
