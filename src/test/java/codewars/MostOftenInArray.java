package codewars;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class MostOftenInArray {

    @Test
    public void test6() {
        int[] A = new int[6];
        A[0] = 20;
        A[1] = 10;
        A[2] = 30;
        A[3] = 30;
        A[4] = 40;
        A[5] = 10;
        assertEquals(10, solution(A));
    }

    @Test
    public void test1() {
        int[] A = new int[6];
        A[0] = 20;
        A[1] = 30;
        A[2] = 30;
        A[3] = 30;
        A[4] = 40;
        A[5] = 10;
        assertEquals(30, solution(A));
    }

    public int solution(int[] A) {
        List<Touple> touples = new ArrayList<>();
        for(int i = 0; i < A.length; i++) {
            int element = A[i];
            int count = 1;
            for(int j = i + 1; j < A.length; j++) {
                if(A[j] == element) {
                    count ++;
                }
            }
            touples.add(new Touple(element, count));
        }
        Comparator<Touple> comparator = (o1, o2) -> Integer.valueOf(o2.getCount()).compareTo(o1.getCount());
        return touples.stream().sorted(comparator).collect(Collectors.toList()).iterator().next().getElement();
    }

    class Touple {

        private final int element;
        private final int count;

        Touple(int element, int count) {
            this.element = element;
            this.count = count;
        }

        public int getElement() {
            return element;
        }

        public int getCount() {
            return count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Touple touple = (Touple) o;
            return element == touple.element &&
                    count == touple.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(element, count);
        }
    }


}
