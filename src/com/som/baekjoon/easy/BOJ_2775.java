package com.baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/* https://www.acmicpc.net/problem/2775 부녀회장이 될테야 */
public class BOJ_2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        HashMap<Integer, int[]> apart = new HashMap<>();
        apart.put(0, new int[15]);
        for (int i = 1; i <= 14; i++) {
            apart.get(0)[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine()); // 층
            int n = Integer.parseInt(br.readLine()); // 호

            for (int j = 1; j <=k; j++) {
                if(apart.get(j) == null) {
                    apart.put(j, new int[15]);
                    apart.get(j)[1] = 1;
                    for (int l = 2; l <= 14; l++) {
                        // 왼쪽 apart.get(j)[l-1] // 아래 apart.get(j-1)[l]
                        apart.get(j)[l] = apart.get(j)[l-1] + apart.get(j-1)[l];
                    }
                }
            }
            sb.append(apart.get(k)[n]).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
