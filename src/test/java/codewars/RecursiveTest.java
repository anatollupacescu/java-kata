package codewars;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecursiveTest {

    @Test
    public void test() {
        assertEquals(3, countRecursive(Arrays.asList(1, 1, 1)));
        assertEquals(3, countTailRecursive(Arrays.asList(1, 1, 1), 0));
    }

    private int countRecursive(List<Integer> integers) {
        if(integers.size() == 1) return integers.iterator().next();
        List<Integer> tail = integers.subList(1, integers.size());
        return integers.get(0) + countRecursive(tail);
    }

    private int countTailRecursive(List<Integer> ints, int acc) {
        int count = acc + ints.iterator().next();
        if(ints.size() == 1) {
            return count;
        }
        return countTailRecursive(ints.subList(1, ints.size()), count);
    }

    @Test
    public void testSubList() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
        assertEquals(Arrays.asList(2, 3, 4), ints.subList(1, ints.size()));
    }
}
