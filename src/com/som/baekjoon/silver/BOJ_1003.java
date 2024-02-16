package com.baekjoon;

import java.io.*;

/**
 *@see<a href="https://www.acmicpc.net/problem/1003">
 * https://www.acmicpc.net/problem/1003 피보나치 함수
 *</a>
 */
public class BOJ_1003 {
    //static int[] fib;
    static int[] fibZero;
    static int[] fibOne;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
//        fib = new int[41];
        fibZero = new int[41];
        fibOne = new int[41];

//        fib[0] = 0;
        fibZero[0]  = 1;
        fibOne[0] = 0;

//        fib[1] = 1;
        fibZero[1]  = 0;
        fibOne[1] = 1;

        for (int i = 2; i <= 40; i++) {
//            fib[i] = fib[i-1] + fib[i-2];
            fibZero[i] = fibZero[i-1] + fibZero[i-2];
            fibOne[i] = fibOne[i-1] + fibOne[i-2];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(fibZero[n]).append(" ").append(fibOne[n]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}
