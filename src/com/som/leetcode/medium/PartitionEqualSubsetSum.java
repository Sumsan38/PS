package com.som.leetcode.medium;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 !=  0) {
            return false;
        }

        // target
        boolean[] numCheck = new boolean[sum + 1];
        numCheck[0] = true;
        for (int num : nums) {
            for (int j = sum; j >= num; j--) {
                if(numCheck[j - num]) {
                    numCheck[j] = true;
                }
            }
        }

        int target = sum / 2;
        return numCheck[target];
    }
}