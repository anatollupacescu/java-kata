package codility;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class SumDistance {

  /*
  41%
   */

  class Solution {

    public int solution(int[] A) {
      int result = -1;
      for (int i = 0; i < A.length; i++) {
        for (int j = i; j < A.length; j++) {
          int c = A[i] + A[j] + (j - i);
          System.out.println(String.format("Sum-distance for (%s, %s) is %s", i, j, c));
          if (c > result) {
            result = c;
          }
        }
      }
      return result;
    }
  }

  @Test
  public void test1() {
    Solution solution = new Solution();
    int[] A = {1, 3, -3};
    assertThat(solution.solution(A), is(equalTo(6)));
  }

  @Test
  public void test2() {
    Solution solution = new Solution();
    int[] A = {-8, 4, 0, 5, -3, 6};
    assertThat(solution.solution(A), is(equalTo(14)));
  }
}
