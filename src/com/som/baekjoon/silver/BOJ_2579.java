package com.baekjoon;

import java.io.*;

/* https://www.acmicpc.net/problem/2579 계단 오르기 */
public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stair = Integer.parseInt(br.readLine());
//        int[] score = new int[stair];
        score = new int[stair];

        for (int i = 0; i < stair; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        br.close();

//        int[][] dp = new int[stair][2]; // 두번째 배열은 repeat 값 // repeat 값은 3까지 갈 수 없다
        dp = new int[stair][2]; // 두번째 배열은 repeat 값 // repeat 값은 3까지 갈 수 없다

        dp[0][0] = score[0];
        dp[0][1] = 1;
        if(stair == 1) {
            System.out.println(dp[0][0]);
            return;
        }
        dp[1][0] = score[0] + score[1];
        dp[1][1] = 2;
        if(stair == 2) {
            System.out.println(dp[1][0]);
            return;
        }
        if(score[1] > score[0]) { // 1층에서 3층을 온 경우 vs 2층부터 시작한 경우
            dp[2][0] = score[1] + score[2];
        } else {
            dp[2][0] = score[0] + score[2];
        }
        dp[2][1] = 2;

        // 반복문으로 푸는 경우
//        for (int i = 3; i < stair; i++) {
//            int previousV = 0;
//            int previousR = 0;
//
//            if(dp[i - 2][0] > dp[i - 3][0] + score[i - 1]) {
//                // n-2 -> n으로 오는 경우 (두 계단 전에서 오는 경우)
//                previousV = dp[i - 2][0];
//                previousR = 1;
//            } else {
//                // n-3 -> n-1 -> n으로 오는 경우 (세 계단 전에서 한 계단 전으로, 거기서 오는 경우)
//                previousV = dp[i - 3][0] + score[i - 1];
//                previousR = 2;
//            }
//            dp[i][0] = previousV + score[i];
//            dp[i][1] = previousR + 1;
//        }
//        System.out.println(dp[stair - 1][0]);
        System.out.println(recordPoint(stair - 1));
    }

    // 재귀함수로 푸는 경우(메모이제이션) // 이게 더 빠르네?
    static int[][] dp;
    static int[] score;
    private static int recordPoint(int location){
        if(dp[location][0] == 0) {
            int previousV = 0;
            int previousR = 0;
            if(recordPoint(location - 2) > recordPoint(location - 3) + score[location - 1]) {
                previousV = recordPoint(location - 2);
                previousR = 1;
            } else {
                previousV = recordPoint(location - 3) + score[location - 1];
                previousR = 2;
            }
            dp[location][0] = previousV + score[location];
            dp[location][1] = previousR;
        }
        return dp[location][0];
    }

}
