package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());
        StringBuilder sb = new StringBuilder();

        // 참조한 소스
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        // dp 맵 채우기
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
            }
        }


        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(token.nextToken()); // 1행 ~ n행까지
            int y1 = Integer.parseInt(token.nextToken()); // 1열 ~ n열까지

            int x2 = Integer.parseInt(token.nextToken());
            int y2 = Integer.parseInt(token.nextToken());

            int answer = dp[x2][y2] - dp[x1 - 1][y2] - dp[x2][y1 - 1] + dp[x1 - 1][y1 - 1];
            sb.append(answer).append("\n");
        }
        // 풀은 방식
        /**
        int[][] table = new int[n][n];
        int[][] tableRowSum = new int[n][n]; // 그 행에 0번 열부터 n번까지 더한 합
        for (int i = 0, sum = 0; i < n; i++, sum = 0) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(token.nextToken());
                sum += value;
                table[i][j] = value;
                tableRowSum[i][j] = sum;
            }
        }

        // 합 계산
        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(token.nextToken()); // 1행 ~ n행까지
            int y1 = Integer.parseInt(token.nextToken()); // 1열 ~ n열까지

            int x2 = Integer.parseInt(token.nextToken());
            int y2 = Integer.parseInt(token.nextToken());


            int answer = 0;
            for (int j = x1 - 1; j <= x2 - 1; j++) {
                int rowTotal = tableRowSum[j][y2 - 1];
                int leftTotal = 0;
                if(y1 >= 2) {
                    leftTotal = tableRowSum[j][y1 - 2];
                }
                answer += (rowTotal - leftTotal);
            }
            sb.append(answer).append("\n");
        }

         */
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

}
