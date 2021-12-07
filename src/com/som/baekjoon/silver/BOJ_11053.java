package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *@see<a href="https://www.acmicpc.net/problem/11053">
 * https://www.acmicpc.net/problem/11053 가장 긴 증가하는 부분 수열
 *</a>
 */
public class BOJ_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(br.readLine());
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        br.close();

        int[] list = new int[a];
        int[] dp = new int[a + 1];

        int length = 0;

        for (int i = 0; i < a; i++) {
            int currentValue = Integer.parseInt(token.nextToken());
            list[i] = currentValue;

            int min = 0;
            for (int j = 0; j < i; j++) {
                if(list[j] < currentValue && min < dp[j+1]) {
                    min = dp[j + 1];
                    dp[i + 1] = min + 1;
                }
            }

            if (dp[i + 1] == 0) dp[i + 1] = 1;
            length = Math.max(dp[i + 1], length);
        }

        System.out.println(length);
    }
}
