package com.som.leetcode.medium;

public class MaximumValueOfOrderedTripletII {
    // Maximum Value of an Ordered Triplet II - LeetCode

    public long maximumTripletValue(int[] nums) {
        long answer = Integer.MIN_VALUE;

        // 0~j까지의 최대값
        int[] prefix = new int[nums.length];
        int leftMax = nums[0];
        for (int jCnt = 1; jCnt < nums.length - 1; jCnt++) {
            int j = nums[jCnt];
            int selectI = nums[jCnt - 1];
            leftMax = Math.max(leftMax, selectI);

            prefix[jCnt] = (leftMax - j);
        }

        int maxPrefix = prefix[0];
        for (int kCnt = 2; kCnt < nums.length; kCnt++) {
            int k = nums[kCnt];
            maxPrefix = Math.max(maxPrefix, prefix[kCnt - 1]);
            long getValue = (long) k * maxPrefix;

            answer = Math.max(answer, getValue);
        }

        return answer < 0? 0L : answer;
    }
}