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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 버스의 개수

        int[][] goCity = new int[n + 1][n + 1];
        int[][] goCost = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine(), " "); // 출발도시 번호, 도착지 도시 번호, 버스 비용
            int firstCity = Integer.parseInt(token.nextToken());
            int secondCity = Integer.parseInt(token.nextToken());

            goCity[firstCity][secondCity] = 1;
            goCost[firstCity][secondCity] = Integer.parseInt(token.nextToken());
        }
        token = new StringTokenizer(br.readLine(), " ");
        int startCity = Integer.parseInt(token.nextToken());
        int endCity = Integer.parseInt(token.nextToken());

        // 방향성, 비용이 있는 우선 탐색
        boolean[] isvisited = new boolean[n + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(startCity);
        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            isvisited[currentCity] = true;
            if(currentCity == endCity) break;
            for (int i = 1; i <= n; i++) {
                if(goCity[currentCity][i] == 1 && !isvisited[i]) {
                    queue.add(i);

                }
            }
        }

        System.out.println("");
    }

}
