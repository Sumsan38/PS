package com.somi.programmers;


import java.util.Arrays;
import java.util.Comparator;

public class InterceptionSystem {
    // [요격 시스템] https://school.programmers.co.kr/learn/courses/30/lessons/181188
    // 그리디
    // 투포인터

    public int solution(int[][] targets) {
        int answer = 0;

        // 시작 오름차순 정렬
        Arrays.sort(targets, Comparator.comparingInt(x -> x[0]));

        // 첫번쨰 요격 포인트
        int start = targets[0][0];
        int end = targets[0][1];
        for (int[] target : targets) {
            if (answer == 0) {
                answer += 1;
                continue;
            }

            int curStart = target[0];
            int curEnd = target[1];

            if(start <= curStart && curStart < end) {
                // 요격 범위 내에 있음
                // 시작 구간은 더 큰 값을 기준 + 끝 구간은 더 작은 값을 기준
                start = curStart;
                end = Math.min(end, curEnd);
            } else {
                // 요격 범위 내에 없음, 다음 요격 범위 검사
                answer++;
                start = curStart;
                end = curEnd;
            }
        }

        return answer;
    }
}