package com.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/12865
 * 평범한 배낭
 * 2022.04.20 16:30 ~ 17:00 브루트포스 구현 시간 초과
 * 참조 https://jeonyeohun.tistory.com/86
 * 2022.04.29 13:00 ~ 13:45 DP 맞았습니다
 * */
public class BOJ_12865 {

    static int bestV = 0;
    static int n;
    static int k;

    static boolean choose[];
    static int[] itemW;
    static int[] values;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer token;
        token = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(token.nextToken()); // 물건의 수 N개
        k = Integer.parseInt(token.nextToken()); // 준서가 버틸 수 있는 무게 K

        dp = new int[k + 1];

        itemW = new int[n];
        values = new int[n];
        choose = new boolean[n];

        for (int i = 0; i < itemW.length; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            itemW[i] = Integer.parseInt(token.nextToken());
            values[i] = Integer.parseInt(token.nextToken());
        }

        selectItem();

        System.out.println(bestV);
    }

    // dp로 푸는 방법 // 100퍼센트에서 틀림
    static int[] dp;
    private static void selectItem(){
        for (int i = 0; i < itemW.length; i++) {
            int w = itemW[i];
            int v = values[i];

            for (int j = k; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
                bestV = Math.max(dp[j], bestV);
            }
        }
    }

    // 브루트포스로 푸는 방법은 시간초과
    private static void chooseItem(int totalweight, int totalvalue, int beforeChoose){
        for (int i = beforeChoose; i < choose.length; i++) {
            int value = totalvalue + values[i];
            int weight = totalweight + itemW[i];
            choose[i] = true;

            if(weight <= k) {
                bestV = Math.max(bestV, value);
                chooseItem(weight, value, i + 1);
            } else {
                continue;
            }

            // 백트레킹으로 고르지 않은 것으로 되돌린다
            choose[i] = false;
        }
    }
}