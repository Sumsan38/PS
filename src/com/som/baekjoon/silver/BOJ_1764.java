package com.baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        ArrayList<String> answerList = new ArrayList<>();

        // 두 개의 리스트에서 겹치는 사람 담은 후에 사전순으로 출력
        HashSet<String> nSet = new HashSet<>(); // HashSet의 특성 순서 없음, 중복값 없음
        for (int i = 0; i < n; i++) {
            nSet.add(br.readLine());
        }

        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if(nSet.contains(name)) answerList.add(name);
        }
        br.close();

        Collections.sort(answerList);
        sb.append(answerList.size()).append("\n");
        for (String word : answerList) {
            sb.append(word).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
