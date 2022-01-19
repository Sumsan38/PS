package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer token;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(token.nextToken());
            int n = Integer.parseInt(token.nextToken());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());

            // 마지막 해의 경우, M과 N의 최소 공배수가된다
            int lastYear = lnm(Math.max(m, n), Math.min(m, n));

            // 참조 1 https://velog.io/@polynomeer/BOJ-6064.-%EC%B9%B4%EC%9E%89-%EB%8B%AC%EB%A0%A5
            // 참조 2 https://mygumi.tistory.com/325
            // <x,y>는 <k%M, k%N>
            // x를 고정해놓고, y 값을 증가시켜 찾는다
            // cnt일 때의 값은, <cnt % m, cnt % n>
            // 여기서 cnt를 M씩 증가시키면서, y값만 N에 따라 증가시킨다

            int cnt = -1;
            for (int k = x; k <= lastYear; k += m) {
                if(k % n == y || (k % n == 0 && n == y)) {
                    cnt = k;
                    break;
                }
            }
            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }
    private static int gcd(int a, int b) {
        if(b == 0) return a;
        else return gcd(b, a % b);
    }
    private static int lnm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
