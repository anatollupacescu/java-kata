package codility;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import org.junit.Test;

public class MinimumInt {

  /* ================ 50%

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.
   */

  private interface Solution {
    int solution(int[] A);
  }

  class Solution1 implements Solution {
    public int solution(int[] A) {
      Arrays.sort(A);
      int prev = 0;
      int i;
      for (i = 0; i < A.length; i++) {
        if (A[i] > 0) {
          if (A[i] > prev + 1) {
            return prev + 1;
          }
          prev = A[i];
        }
      }
      int result = A[i-1];
      return (result <= 0) ? 1 : result + 1;
    }
  }

  class Solution2 implements Solution {
    public int solution(int[] A) {
      Arrays.sort(A);

      if (A[0] > 1 || A[A.length - 1] < 0) return 1;

      int prev = 0;
      for (int i = 0; i < A.length; i++) {
        if (A[i] > 0) {
          if (A[i] > prev + 1) {
            return prev + 1;
          }
          prev = A[i];
        }
      }
      return A[A.length - 1] + 1;
    }
  }

  private Solution getSolution() {
    return new Solution2();
  }

  @Test
  public void test3() {
    Solution solution  = getSolution();
    int[] A = {0, 0, 0};
    assertThat(solution.solution(A), is(equalTo(1)));
  }

  @Test
  public void test() {
    Solution solution  = getSolution();
    int[] A = {1, 2, 3};
    assertThat(solution.solution(A), is(equalTo(4)));
  }

  @Test
  public void test1() {
    Solution solution  = getSolution();
    int[] A = {-1, 0, 2, 3};
    assertThat(solution.solution(A), is(equalTo(1)));
  }

  @Test
  public void test2() {
    Solution solution  = getSolution();
    int[] A = {-1, -3};
    assertThat(solution.solution(A), is(equalTo(1)));
  }

  @Test
  public void test5() {
    Solution solution  = getSolution();
    int [] A = {1, 3, 6, 4, 1, 2};
    assertThat(solution.solution(A), is(equalTo(5)));
  }
}
