package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* https://www.acmicpc.net/problem/13458
* [삼성 SW 역량 테스트 기출 문제]
* 시험 감독
* */
public class BOJ_13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer cadiToken = new StringTokenizer(br.readLine(), " ");
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        br.close();

        int b = Integer.parseInt(token.nextToken()); // 총감독관 총 감시 응시자 수 B명 // 한 방엔 반드시 한명
        int c = Integer.parseInt(token.nextToken()); // 부감독관 총 감시 응시자 수 C명

        long answer = 0;
        for (int i = 0; i < n; i++) {
            int candidate = Integer.parseInt(cadiToken.nextToken());
            answer++;
            candidate = candidate - b;
            if(candidate <= 0) continue;

            answer += (candidate / c);
            if(candidate % c != 0) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }

}
