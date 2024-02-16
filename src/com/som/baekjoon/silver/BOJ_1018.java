package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/1018 체스판 다시 칠하기 */
public class BOJ_1018 {
    static char[][] startWChess = {
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'}
    };
    static char[][] startBChess = {
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B'}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        char[][] chess = new char[n][m];
        for (int i = 0; i < n; i++) {
            String read = br.readLine();
            for (int j = 0; j < m; j++) {
                chess[i][j] = read.charAt(j);
            }
        }
        br.close();

        int result = 64;
        // 시작점
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                int startBChange = 0;
                int startWChange = 0;

                // 해당 위치에서 8x8 체스판 색칠 비교
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        char current = chess[i + k][j + l];
                        if(startBChess[k][l] != current) startBChange++;
                        if(startWChess[k][l] != current) startWChange++;
                    }
                }
                result = Integer.min(result, startBChange);
                result = Integer.min(result, startWChange);

                if(result == 0) {
                    System.out.println("0");
                    return;
                }
            }
        }

        System.out.println(result);
    }

}
