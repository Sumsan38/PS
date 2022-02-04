package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        br.close();

        long a = Integer.parseInt(token.nextToken());
        long b = Integer.parseInt(token.nextToken());
        long c = Integer.parseInt(token.nextToken());

        // answer = a^b % c
        // 거듭제곱의 모듈러 연산 계산법
        System.out.println(dp(a, b, c));
    }

    private static long dp(long a, long b, long c){
        if(b == 1) return a % c;
        else if(b == 0) return 1;
        else {
            if(b % 2 == 0) {
                long before = dp(a, b/2, c);
                return (before * before) % c;
            } else {
                long before = dp(a, (b-1)/2, c);
                return (a * ((before * before) % c)) % c;
            }
        }
    }
}
