package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/2609 최대공약수와 최소공배수 */
public class BOJ_2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int[] nums = {Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())};
        br.close();

        // 유클리드 호제법으로 구하는 방법
        Arrays.sort(nums);
        int gcd = gcd(nums[0], nums[1]);

        System.out.println(gcd);
        System.out.println(nums[0] * nums[1] / gcd);

        // 구현한 방법.
//        if(nums[0] == nums[1]) {
//            System.out.println(nums[0]);
//            System.out.println(nums[0]);
//            return;
//        }
//        int gcd = 1;
//        int divisor = 1;
//        while(true) {
//            int checkNum = Math.max(nums[0], nums[1]);
//            for (int i = 2; i <= checkNum; i++) {
//                if(i == checkNum) {
//                    System.out.println(gcd);
//                    System.out.println(gcd * nums[0] * nums[1]);
//                    return;
//                }
//                if(nums[0] % i == 0 && nums[1] % i == 0) {
//                    nums[0] = nums[0] / i;
//                    nums[1] = nums[1] / i;
//                    divisor = i;
//                    gcd = gcd * divisor;
//                    break;
//                }
//            }
//        }
    }
    static int gcd(int big, int small) {
        if(small == 0) return big;
        else return gcd(small, big % small);
    }
}
