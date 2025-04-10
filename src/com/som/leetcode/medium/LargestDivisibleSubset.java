package com.som.leetcode.medium;

public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int max = Integer.MIN_VALUE;
        int maxIdx = 0;
        for (int iCnt = 0; iCnt < nums.length; iCnt++) {
            for (int jCnt = iCnt - 1; jCnt >= 0; jCnt--) {
                if(nums[iCnt] % nums[jCnt] == 0) {
                    if(dp[jCnt] + 1 > dp[iCnt]) {
                        dp[iCnt] = dp[jCnt] + 1;
                    }
                }
            }

            if(dp[iCnt] > max) {
                max = dp[iCnt];
                maxIdx = iCnt;
            }
        }

        List<Integer> answers = new ArrayList<>();
        int currentNum = nums[maxIdx];
        int currentDp = dp[maxIdx];

        for (int i = maxIdx; i >= 0; i--) {
            if(currentNum % nums[i] == 0 && dp[i] == currentDp) {
                answers.add(nums[i]);
                currentNum = nums[i];
                currentDp--;
            }
        }

        return answers;
    }
}