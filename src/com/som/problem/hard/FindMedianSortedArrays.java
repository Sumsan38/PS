package com.som.problem.hard;

import java.util.ArrayList;

public class FindMedianSortedArrays {
	// https://leetcode.com/problems/median-of-two-sorted-arrays/
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double middleValue = 0.0;
		// TODO O(log(N+M))
		// nums1 length = M / nums2 length = N
		int numsOne = 0;
		int numsTwo = 0;
		
		ArrayList<Integer> sortList = new ArrayList<>();
		for (int i = 0; i < (nums1.length + nums2.length) / 2; i++) {
			// add low value
			if(nums1[numsOne] == nums2[numsTwo]){
				sortList.add(nums1[numsOne]);
				numsOne++;
				numsTwo++;
			} else if(nums1[numsOne] > nums2[numsTwo]) {
				sortList.add(nums2[numsTwo]);
				numsTwo++;
			} else {
				sortList.add(nums1[numsOne]);
				numsOne--;
			}
		}
		
		
		
		return middleValue;
	}

}
