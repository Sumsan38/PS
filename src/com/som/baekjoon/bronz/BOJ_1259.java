package com.baekjoon;

import java.io.*;

/*
* https://www.acmicpc.net/problem/1259 팰린드롬의 수
* */
public class BOJ_1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String read = br.readLine();
            if(read.equals("0")) break;
            int start = 0;
            int end = read.length() - 1;
            boolean isFalend = true;

            for (int i = 0; i < read.length() / 2; i++) {
                if(read.charAt(start++) != read.charAt(end--)) {
                    isFalend = false;
                    break;
                }
            }
            if(isFalend) sb.append("yes").append("\n");
            else sb.append("no").append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
