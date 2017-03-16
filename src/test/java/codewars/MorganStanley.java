package codewars;

import org.junit.Test;

import static org.junit.Assert.*;

public class MorganStanley {

    final int MAX = 1_000_000_000;

    public int solution(int[] A) {
        if(A.length < 2) return 0;
        return countPairs(A);
    }

    private int countPairs(int[] a) {
        int pairCount = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i + 1; j < a.length; j++)
                if(a[i] == a[j]) {
                    System.out.println(i + " " + j);
                    if(pairCount >= MAX) {
                        return MAX;
                    }
                    pairCount ++;
                }
        }
        return pairCount;
    }

    @Test
    public void test1() {
        int[] arr = new int[] {3, 3 };
        int solution = solution(arr);
        assertEquals(4, solution);
    }
}
