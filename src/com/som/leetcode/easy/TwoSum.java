package com.som.leetcode.easy;

public class TwoSum {
	// https://leetcode.com/problems/two-sum/
	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		throw new IllegalArgumentException("No value in Array");
	}

}
