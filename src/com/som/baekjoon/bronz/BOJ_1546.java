package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/1546 평균 */
public class BOJ_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        br.close();

        int bestScore = 0;
        int[] score = new int[n];
        for (int i = 0; i < score.length; i++) {
            score[i] = Integer.parseInt(token.nextToken());
            bestScore = Math.max(bestScore, score[i]);
        }

        double sum = 0;
        for (int i = 0; i < score.length; i++) {
            sum = sum + ((double) score[i] / (double) bestScore * 100);
        }
        System.out.println(sum / n);
    }
}
