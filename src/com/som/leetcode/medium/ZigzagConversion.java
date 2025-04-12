package com.som.leetcode.medium;

public class ZigzagConversion {
    // https://leetcode.com/problems/zigzag-conversion/description/

    public String convert(String s, int numRows) {
        if(numRows <= 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] rowBuilders = new StringBuilder[numRows];
        for (int i = 0; i < rowBuilders.length; i++) {
            rowBuilders[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean downRow = false;
        for (char c : s.toCharArray()) {
            rowBuilders[currentRow].append(c);
            if (currentRow == 0 || currentRow == numRows - 1) {
                downRow = ! downRow;
            }

            currentRow += downRow ? 1 : -1;
        }


        StringBuilder answer = new StringBuilder();
        for (StringBuilder rowBuilder : rowBuilders) {
            answer.append(rowBuilder.toString());
        }

        return answer.toString();
    }

}