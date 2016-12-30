package codewars;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class FindOutlierTest {

    private static class FindOutlier {

        final static IntUnaryOperator rest = i -> Math.abs(i) % 2;
        final static IntPredicate is_odd = i -> rest.applyAsInt(i) == 0;
        final static IntPredicate is_even = is_odd.negate();


        static int myFind(int[] integers) {
            int sum = Arrays.stream(integers).limit(3).map(rest).sum();
            IntPredicate pred = (sum > 1) ? is_odd : is_even;
            int size = integers.length;
            if (sum != 0 && sum != 3) {
                size = 3;
            }
            return Arrays.stream(integers).limit(size).filter(pred).findFirst().getAsInt();
        }
        public static int properFind(int[] integers) {
            int sum = Arrays.stream(integers).limit(3).map(rest).sum();
            IntPredicate filter = (sum == 0 || sum == 1) ? is_even : is_odd;
            IntStream stream = Arrays.stream(integers).parallel();
            if (sum == 1 || sum == 2) stream = Arrays.stream(integers).limit(3);
            return stream.filter(filter).findFirst().getAsInt();
        }

        public static int find(int[] exampleTest1) {
            return myFind(exampleTest1);
        }
    }

    @Test
    public void testExample() {
        int[] exampleTest1 = {2, 6, 8, -10, 3};
        int[] exampleTest2 = {206847684, 1056521, 7, 17, 1901, 21104421, 7, 1, 35521, 1, 7781};
        int[] exampleTest3 = {Integer.MAX_VALUE, 0, 1};
        assertEquals(3, FindOutlier.find(exampleTest1));
        assertEquals(206847684, FindOutlier.find(exampleTest2));
        assertEquals(0, FindOutlier.find(exampleTest3));
    }
}
