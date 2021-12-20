package com.baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/* https://www.acmicpc.net/problem/1181 단어 정렬 */
public class BOJ_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        for (int i = 0; i <= 50; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            list.get(word.length()).add(word);
        }
        br.close();

        for (int i = 1; i <= 50; i++) {
            list.get(i).sort(null); // 단어 순 정렬 (원래 단어순 정렬이었음!)
            String before = "";
            for (int j = 0; j < list.get(i).size(); j++) {
                String read = list.get(i).get(j);
                if(before.equals(read)) continue;
                else {
                    sb.append(read).append("\n");
                    before = read;
                }
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
