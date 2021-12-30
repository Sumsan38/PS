package com.baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/1620 나는야 포켓몬 마스터 이다솜 */
public class BOJ_1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        HashMap<String, Integer> pocketmon = new HashMap<>();
        String[] pocketmonList = new String[n];
        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            pocketmon.put(name, i + 1);
            pocketmonList[i] = name;
        }

        for (int i = 0; i < m; i++) {
            String answer = br.readLine();
            char front = answer.charAt(0);

            if('9' >= front && front >= '0') {
                // 숫자가 들어온 경우
                int num = Integer.parseInt(answer);
                sb.append(pocketmonList[num - 1]).append("\n");
            } else {
                // 포켓몬 이름이 들어온 경우
                sb.append(pocketmon.get(answer)).append("\n");
            }
        }

        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
