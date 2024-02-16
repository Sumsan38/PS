package com.baekjoon;

import java.io.IOException;
import java.util.Scanner;

/**
 *@see<a href="https://www.acmicpc.net/problem/14510">
 * https://www.acmicpc.net/problem/14501 퇴사
 *</a>
 */
public class BOJ_14501 {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        // 해당 문제가 다이나믹 프로그래밍인 이유는?
        // 한번의 선택으로
        // 다음의 문제가 그리디 알고리즘이나 브르투포스 알고리즘이라고 생각했던 이유는?
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] t = new int[N]; // 상담일수
        int[] p = new int[N]; // 상담 비용
        dp = new int[N + 1]; // N일 까지 일한 비용
        for (int i = 0; i < N; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }
        sc.close();
        //점화식
        //현재 날짜에서 소요 시간과 비용을 더해 dp에 저장한다. 이후, 중복될 때 최대값을 넣는다.
        for (int i=0; i<N; i++) {
            if (i + t[i] <= N) {
                // i + t[i] i일에 일이 시작하고, 끝나는 날 i + t[i] // 따라서 dp[i + t[i]] 값이 갱신된다
                // dp[i + t[i]] // 저장되어 있던, i + t[i]까지 일했을 때 받는 금액 최고치
                // dp[i] + p[i] // i일 까지 일한 요금 최대치 + t[i]까지 일한 만큼의 금액 (i~i+t[i]일까지 일 못함 어차피)
                // 위의 두 값을 비교해서 큰 값을 넣는다
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }
            //현재 경우의 수가 0일 수 있기 때문에 이전의 최대값을 넣어줌.
            //해당 날짜에 일할 수 없다면, 이전까지 일한 최대 수당을 넣어주어야 한다.
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }
        System.out.println(dp[N]);
    }
}