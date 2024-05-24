package com.som.programmers.easy;

import java.util.Arrays;
import java.util.regex.Pattern;

public class DartGame {
    // https://school.programmers.co.kr/learn/courses/30/lessons/17682

    // 1	1S2D*3T	    37	11 * 2 + 22 * 2 + 33
    // 2	1D2S#10S	9	12 + 21 * (-1) + 101
    // 3	1D2S0T	    3	12 + 21 + 03
    // 4	1S*2T*3S	23	11 * 2 * 2 + 23 * 2 + 31
    // 5	1D#2S*3S	5	12 * (-1) * 2 + 21 * 2 + 31
    // 6	1T2D3D#	    -4	13 + 22 + 32 * (-1)
    // 7	1D2S3T* 	59	12 + 21 * 2 + 33 * 2
    public int solution(String dartResult) {
        String REGEXP_PATTERN_NUMBER = "^[\\d]*$";

        int cnt = 0;
        String[] resultStr = new String[]{"","",""};
        int[] results = new int[3];
        for (int at = 0; at < dartResult.length(); at++) {
            String charAt = String.valueOf(dartResult.charAt(at));

            // 점수
            if(Pattern.matches(REGEXP_PATTERN_NUMBER, charAt)) {
                resultStr[cnt] = resultStr[cnt] + charAt;
            }
            // S, D, T
            else if(charAt.equalsIgnoreCase("S") || charAt.equalsIgnoreCase("D") || charAt.equalsIgnoreCase("T")) {
                int score = Integer.parseInt(resultStr[cnt]);
                resultStr[cnt] = resultStr[cnt] + charAt;

                int pow = 1;
                if(charAt.equalsIgnoreCase("D")) pow = 2;
                else if(charAt.equalsIgnoreCase("T")) pow = 3;

                score = (int) Math.pow(score, pow);
                results[cnt] = score;

                cnt++;
            }
            // *, #
            else if(charAt.equalsIgnoreCase("*") || charAt.equalsIgnoreCase("#")) {
                resultStr[cnt - 1] = resultStr[cnt - 1] + charAt;
                if(charAt.equalsIgnoreCase("*")) {
                    // 현재, 이전 점수 2개만 * 2배
                    for (int i = 1; i <= 2; i++) {
                        if(cnt - i >= 0) {
                            results[cnt - i] = results[cnt - i] * 2;
                        }
                    }
                } else {
                    results[cnt - 1] = results[cnt - 1] * -1;
                }
            }
        }

        return Arrays.stream(results).sum();
    }
}
