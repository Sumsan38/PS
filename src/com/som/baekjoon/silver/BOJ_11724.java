package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/11724 연결 요소의 개수 */
public class BOJ_11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(token.nextToken()); // 정점의 개수
        int m = Integer.parseInt(token.nextToken()); // 간선의 양 끝점

        int answer = 0;
        boolean[] isVisit = new boolean[n + 1];
        int[][] list = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(token.nextToken());
            int end = Integer.parseInt(token.nextToken());

            list[start][end] = list[end][start] = 1;
        }

        for (int i = 1; i <= n; i++) {
            if(isVisit[i] == false) {
                answer++;
                checkVisited(list, isVisit, i);
            }
        }

        System.out.println(answer);
        br.close();
    }

    // using DFS
    private static void checkVisited(int[][] list, boolean[] isVisit, int currentPoint) {
        if(isVisit[currentPoint]) return;

        isVisit[currentPoint] = true;
        // currentPoint에 닿아 있는 애들 전부 isVisit를 true로 만들기
        int[] checklist = list[currentPoint];
        for (int i = 1; i < checklist.length; i++) {
            if(checklist[i] == 1 && isVisit[i] == false) {
                checkVisited(list, isVisit, i);
            }
        }
    }
}
