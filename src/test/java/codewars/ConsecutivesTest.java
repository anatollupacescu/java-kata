package codewars;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class ConsecutivesTest {

    private static class Consecutives {

        public static List<Integer> mySumConsecutives(List<Integer> i) {
            final List<Integer> result = new ArrayList<>();
            i.stream().forEach(
                    new Consumer<Integer>() {
                        int prev;
                        boolean isFirst = true;
                        int index = 0;
                        int sum;
                        public void accept(Integer integer) {
                            index++;
                            if(isFirst) {
                                sum += integer;
                                prev= integer;
                                isFirst = false;
                            } else if (integer.equals(prev)) {
                                sum += integer;
                            } else {
                                result.add(sum);
                                prev = sum = integer;
                            }
                            if(index == i.size()) {
                                result.add(sum);
                            }
                        }
                    });
            return result;
        }

        public static List<Integer> bestSumConsecutives(List<Integer> s) {
            int previous = Integer.MAX_VALUE;
            LinkedList<Integer> l = new LinkedList<>();
            for (Integer v: s){
                l.add(v == previous? l.pollLast() + v : v);
                previous = v;
            }
            return l;
        }

        public static List<Integer> sumConsecutives(List<Integer> i) {
            return mySumConsecutives(i);
        }
    }

    @Test
    public void test1() {
        System.out.println("Basic Tests");
        List<Integer> i = Arrays.asList(1, 4, 4, 4, 0, 4, 3, 3, 3);
        List<Integer> o = Arrays.asList(1, 12, 0, 4, 9);
        System.out.println("Input: {1,4,4,4,0,4,3,3,1}");
        assertEquals(o, Consecutives.sumConsecutives(i));
    }
        @Test
    public void test() {
        System.out.println("Basic Tests");
        List<Integer> i = Arrays.asList(1,4,4,4,0,4,3,3,1);
        List<Integer> o = Arrays.asList(1,12,0,4,6,1);
        System.out.println("Input: {1,4,4,4,0,4,3,3,1}");
        assertEquals(o, Consecutives.sumConsecutives(i));
        i = Arrays.asList(-5,-5,7,7,12,0);
        o = Arrays.asList(-10,14,12,0);
        System.out.println("Input: {-5,-5,7,7,12,0}");
        assertEquals(o, Consecutives.sumConsecutives(i));
    }
}
