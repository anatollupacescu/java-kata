package codility;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import org.junit.Test;

public class SquareIntegers {

  /*
  60%

  how many square integers are within range
   */

  class Solution {
    public int solution(int A, int B) {
      int count = 0;

      double rootOfA = Math.sqrt(A);

      if (rootOfA % 1 != 0) {
        rootOfA++;
      }

      double i = rootOfA * rootOfA;
      do {
        if (i <= B && i >= A) {
          count++;
        }
        rootOfA++;
        i = rootOfA * rootOfA;
      } while (i <= B);

      return count;
    }
  }

  @Test
  public void test1() {
    Solution solution  = new Solution();
    assertThat(solution.solution(4, 4), is(equalTo(1)));
  }

  @Test
  public void test2() {
    Solution solution  = new Solution();
    assertThat(solution.solution(4, 9), is(equalTo(2)));
  }

  @Test
  public void test3() {
    Solution solution  = new Solution();
    assertThat(solution.solution(4, 17), is(equalTo(3)));
  }
}
