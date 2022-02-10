package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_15650 {
    static int n;
    static int m;
    static int[] array;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        br.close();

        array = new int[m];
        // DFS 사용 // 백트래킹
        dfs(1, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int value, int depth){
        if(depth == m) {
            for (int i : array) {
                sb.append(i + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = value; i <= n; i++) {
            array[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }
}
