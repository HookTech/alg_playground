package ps.philo.playground.leetcode.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author philo
 * @create 2019-01-1/15/2019 9:55 AM
 */
public class P9_PlindromicInteger {
  @Test
  public void test() {
    Assert.assertEquals(plindromicInteger(121), true);
    Assert.assertEquals(plindromicInteger(-121), false);
    Assert.assertEquals(plindromicInteger(10), false);
    Assert.assertEquals(plindromicInteger(1221), true);
  }

  public boolean plindromicInteger(int n) {
    if (n < 0) return false;
    int num = n, count = 1;
    while (num / 10 != 0) {
      num = num / 10;
      count++;
    }
    int[] members = new int[count];
    for (int j = 0; j < count; j++) {
      members[j] = n % 10;
      n = n / 10;
    }
    int s = 0, e = count - 1;
    while (s < e) {
      if (members[s] != members[e]) {
        return false;
      }
      s++;
      e--;
    }
    return true;
  }
}
