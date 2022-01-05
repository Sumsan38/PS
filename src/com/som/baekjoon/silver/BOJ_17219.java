package com.baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        HashMap<String, String> password = new HashMap<>();
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            password.put(token.nextToken(), token.nextToken());
        }

        for (int i = 0; i < m; i++) {
            String address = br.readLine();
            sb.append(password.get(address)).append("\n");
        }
        br.close();


        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }
}
