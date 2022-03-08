package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;
/*
 * https://www.acmicpc.net/problem/1149 RGB거리
 * */
public class BOJ_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 각각의 집을 선택했을 때(R, G, B)의 cost를 기록
        // 예를 들어 빨간색(R)을 선택, 그러면 전 집의 녹색(G), 파란색(B) cost에서 최소 코스트 선택 후 결정
        int[][] cost = new int[n][3];

        String[] rgb = br.readLine().split(" ");
        for (int i = 0; i < 3; i++) cost[0][i] = Integer.parseInt(rgb[i]);

        for (int i = 1; i < n; i++) {
            rgb = br.readLine().split(" ");

            cost[i][0] = Integer.parseInt(rgb[0]) + Math.min(cost[i - 1][1], cost[i - 1][2]); // R 칠하기
            cost[i][1] = Integer.parseInt(rgb[1]) + Math.min(cost[i - 1][0], cost[i - 1][2]); // G 칠하기
            cost[i][2] = Integer.parseInt(rgb[2]) + Math.min(cost[i - 1][0], cost[i - 1][1]); // B 칠하기
        }

        System.out.println(Math.min(cost[n - 1][0], Math.min(cost[n - 1][1], cost[n - 1][2])));
    }


}