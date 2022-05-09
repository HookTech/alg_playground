package ps.philo.playground.play;

import org.testng.annotations.Test;

public class PrintCharsTest {
    @Test
    public void test() {
        String s = "12345";
        arrange(s.toCharArray(), 0, s.length());
    }

    public void arrange(char[] words, int start, int len) {
        if (start == len - 1) {
            for (char w : words) {
                System.out.print(w);
            }
            System.out.println();
        } else {
            for (int i = start; i < len; i++) {
                if (!isRepeate(words, start, i)) {
                    if (words[start] != words[i]) {
                        swap(words, start, i);
                    }
                    arrange(words, start + 1, len);
                    if (words[start] != words[i]) {
                        swap(words, start, i);
                    }
                }
            }
        }
    }

    private boolean isRepeate(char[] words, int from, int to) {
        for (int k = from; k < to; k++) {
            if (words[k] == words[to]) return true;
        }
        return false;
    }

    private void swap(char[] words, int i, int j) {
        char tmp = words[i];
        words[i] = words[j];
        words[j] = tmp;
    }
}
