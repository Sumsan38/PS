package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1916
 * 최소비용 구하기
 * */
public class BOJ_1916 {

    static long[] totalCost;
    static boolean[] isvisited;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 버스의 개수

        int[][] goCost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i==j) {
                    goCost[i][j] = 0;
                    continue;
                }
                goCost[i][j] = Integer.MAX_VALUE-1; // 우선 모든 비용 MAX로 셋팅
            }
        }

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine(), " "); // 출발도시 번호, 도착지 도시 번호, 버스 비용
            int firstCity = Integer.parseInt(token.nextToken());
            int secondCity = Integer.parseInt(token.nextToken());
            int cost = Integer.parseInt(token.nextToken());

            if(goCost[firstCity][secondCity] == -1) {
                goCost[firstCity][secondCity] = cost;
            } else if(goCost[firstCity][secondCity] > cost)
                goCost[firstCity][secondCity] = cost;
        }

        token = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(token.nextToken());
        int end = Integer.parseInt(token.nextToken());

        // 방향성, 비용이 있는 우선 탐색
        totalCost = new long[n + 1]; // 출발 도시에서 도착 도시까지의 cost 값
        isvisited = new boolean[n + 1];

        // 초기화 // start 지점에서의 cost로 셋팅
        for (int i = 1; i <= n; i++) {
            totalCost[i] = goCost[start][i];
        }
        isvisited[start] = true; // start 갔었다고 셋팅

        // 1개는 이미 갔으니까
        for (int i = 0; i < n - 1; i++) {
            int cur = getMinIdx();

        }



        for (int i = 0; i < n - 1; i++) {
            int cur = getMinIdx();
            isvisited[cur] = true;

            for (int j = 1; j <= n; j++) {
                if(!isvisited[j]) {
                    if(totalCost[cur] + goCost[cur][j] < totalCost[j]) {
                        totalCost[j] = totalCost[cur] + goCost[cur][j];
                    }
                }
            }

        }
        System.out.println(totalCost[end]);
    }

    public static int getMinIdx() {
        long min = Integer.MAX_VALUE;
        int idx = 0;

        for (int i = 1; i <= n; i++) {
            if(totalCost[i] < min && !isvisited[i]) {
                min = totalCost[i];
                idx = i;
            }
        }
        return idx;
    }

}