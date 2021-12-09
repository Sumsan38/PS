package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/2805 나무 자르기
 * */
public class BOJ_2805 {
    public static void main(String[] args) throws IOException {
        // 브루투포스 방식으로 진행할 경우 시간초과가 난다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        StringTokenizer treeToken = new StringTokenizer(br.readLine(), " ");
        br.close();

        int n = Integer.parseInt(token.nextToken());
        int m = Integer.parseInt(token.nextToken());

        int height = 0;

        int[] tree = new int[n];
        int start = 0;
        int end = 0;
        int middle = 0; // 자르는 지점이 mid가 된다

        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.parseInt(treeToken.nextToken());
            end = Math.max(end, tree[i]);
        }
        Arrays.sort(tree);


        while (start < end) {
            middle = (start + end) / 2;

            long sum = 0;
            for (int i = 0; i < tree.length; i++) {
                if(tree[i] < middle) continue;
                sum += (tree[i] - middle);
            }

            if(sum == m) {
                end = middle + 1;
                break;
            } else if(sum > m) {
                start = middle + 1; // 너무 나무를 많이 자름 // 자르는 선을 올려야함
            } else {
                end = middle; // 너무 나무가 적음
            }
        }
        System.out.println(end - 1);
    }
}