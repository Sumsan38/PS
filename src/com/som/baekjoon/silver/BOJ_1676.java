package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1676 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        // 소인수분해로 해당 문제를 접근했어야한다;;;
        // 참조 https://st-lab.tistory.com/165
        int cnt = 0;
        while(n >= 5) {
            cnt += (n / 5);
            n /= 5;
        }

        System.out.println(cnt);


//        dp[0] = 1;
//        dp[1] = 1;
//        factorier(n);
    }

    // 틀린 방법 // 예시로 25일 경우
//    static int[] dp = new int[501];
//    static int cnt = 0;
//    public static int factorier(int n){
//        // 처음 뒷자리 0이 나온 순간, 뭘곱해도 거긴 0의 자리이다.
//        // 처음 뒷자리 0이 나온다면, 카운트를 올리고 10으로 나눈 값을 넣어준다
//        if(dp[n] == 0) {
//            int value = n * factorier(n-1);
//            while(value % 10 == 0){
//                cnt++;
//                value = value / 10; // 1의 자리만 가지고 있으면 된다
//            }
//            value = value % 10;
//            dp[n] = value;
//        }
//        return dp[n];
//    }
}
