package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11050 이항 계수 1
 */
public class BOJ_11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        br.close();

        int n = Integer.parseInt(token.nextToken());
        int k = Integer.parseInt(token.nextToken());

        // 이항 계수란, n개 중에 k개를 고르는 방법
        // n! / ( (n-k)! * k!)

        int[] fac = new int[n + 1];
        fac[0] = 1;
        fac[1] = 1;
        for (int i = 2; i <= n; i++) {
            fac[i] = i * fac[i - 1];
        }

        System.out.println(fac[n] / (fac[n-k] * fac[k]));
    }

}
