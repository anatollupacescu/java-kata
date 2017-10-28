package codewars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StaircaseTest {

    @Test
    public void test1() {
        stairCase(6);
    }

    static void stairCase(int n) {
        for(int i = 1; i <= n; i++) {
            System.out.println(printOne(n, i));
        }
    }

    private static String printOne(int total, int current) {
        char[] one = new char[total];
        for(int i = 0; i < total; i++) {
            if(i >= total - current) {
                one[i] = '#';
            } else {
                one[i] = ' ';
            }
        }
        return new String(one);
    }
}
