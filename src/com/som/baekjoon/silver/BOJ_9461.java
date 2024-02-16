package com.baekjoon;

import java.io.*;

public class BOJ_9461 {
    static long[] p = new long[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        p[1] = p[2] = p[3] = 1;
        p[4] = p[5] = 2;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(get(n)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static long get(int n){
        if(p[n] == 0) {
            p[n] = get(n - 1 - 4) + get(n - 1);
        }

        return p[n];
    }
}
