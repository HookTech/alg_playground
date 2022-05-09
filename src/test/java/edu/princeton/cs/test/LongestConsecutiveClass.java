package edu.princeton.cs.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveClass implements RecursionAndDinamicCallMethod {

    private static int maxNum = 1;

    @Override
    public void callRecursionAndDinamicAlgoMethod() {
        TestUtil.printString("++++++++++++++++ longestConsecutiveTest ++++++++++++++++");
        longestConsecutiveTest();
    }

    @Test(groups = RecursionAndDinamicCallMethod.testBaseName)
    public void longestConsecutiveTest() {
        int[] rawArray = new int[]{100, 4, 200, 1, 9, 2, 5, 7, 8};
        Assert.assertEquals(longestConsecutiveNumOfArray(rawArray), 3);
    }

    /*** self practice function **/
    public int longestConsecutiveNumOfArray(int[] arr) {
        Map<Integer, Integer> recordsMap = new HashMap<>();
        for (int cur : arr) {
            if (!recordsMap.containsKey(cur)) {
                recordsMap.put(cur, 1);
            }
            if (recordsMap.containsKey(cur - 1) && !recordsMap.containsKey(cur + 1)) {
                updateMapFromStartIndex2EndIndex(
                        recordsMap,
                        cur - recordsMap.get(cur - 1),
                        cur,
                        recordsMap.get(cur - 1) + 1);
            } else if (!recordsMap.containsKey(cur - 1) && recordsMap.containsKey(cur + 1)) {
                updateMapFromStartIndex2EndIndex(
                        recordsMap,
                        cur,
                        cur + recordsMap.get(cur + 1),
                        recordsMap.get(cur + 1) + 1);
            } else if (recordsMap.containsKey(cur - 1) && recordsMap.containsKey(cur + 1)) {
                updateMapFromStartIndex2EndIndex(recordsMap,
                        cur - recordsMap.get(cur - 1),
                        cur + recordsMap.get(cur + 1),
                        recordsMap.get(cur - 1) + recordsMap.get(cur + 1) + 1);
            } else {
                continue;
            }

        }
        TestUtil.printMap(recordsMap);
        return maxNum;
    }

    /**
     * update recordsMap until end, end one is updated too
     * and it will update maxNum
     */
    private void updateMapFromStartIndex2EndIndex(Map<Integer, Integer> recordsMap, Integer start, Integer end, Integer updatedNum) {
        for (int i = start; i <= end; i++) {
            recordsMap.put(i, updatedNum);
        }
        if (updatedNum > maxNum) {
            maxNum = updatedNum;
        }
    }
}
