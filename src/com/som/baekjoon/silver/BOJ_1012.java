package com.baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer token;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(token.nextToken()); // 배추밭 가로 길이 // x // w
            int n = Integer.parseInt(token.nextToken()); // 배추밭 세로 길이 // y // h
            int k = Integer.parseInt(token.nextToken()); // 배추가 심어져 있는 위치의 개수

            int[][] cabbege = new int[n][m]; // n행 m열의 배열 // y, x // h, w
            for (int j = 0; j < k; j++) {
                token = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(token.nextToken()); // m에 매핑된다
                int y = Integer.parseInt(token.nextToken()); // n에 매핑된다
                cabbege[y][x] = 1;
            }

            int result = 0;
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < m; l++) {
                    if(cabbege[j][l] == 1) {
                        result++;
                        fillZero(cabbege, l, j);
                    }
                }
            }
            sb.append(result).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // DFS로 푸는 법
    private static void fillZero(int[][] cabbege, int x, int y){
        // 위, 아래, 오른쪽, 왼쪽 0으로 채우기
        int w = cabbege[0].length;
        int h = cabbege.length;

        if(x>= 0 && x < w && y >= 0 && y < h && cabbege[y][x] == 1) {
            cabbege[y][x] = 0;

            fillZero(cabbege, x - 1, y);
            fillZero(cabbege, x + 1, y);
            fillZero(cabbege, x, y + 1);
            fillZero(cabbege, x, y - 1);
        }
    }


}
