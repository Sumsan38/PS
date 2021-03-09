package com.som.problem.medium;

public class LengthOfLongestSubstring {
	// https://leetcode.com/problems/longest-substring-without-repeating-characters/
	public int lengthOfLongestSubstring(String s) {
		if (s.length() <= 1) {
			return s.length();
		}
		StringBuilder sb = new StringBuilder();
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			String addString = s.substring(i, i + 1);
			if (sb.toString().contains(addString)) {
				if (sb.length() > result) {
					result = sb.toString().length();
				}
				sb.delete(0, sb.indexOf(addString) + 1);
			}

			sb.append(addString);
		}
		if (sb.toString().length() > result) {
			result = sb.toString().length();
		}
		return result;
	}

}
