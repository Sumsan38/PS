package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        char[] read = br.readLine().toCharArray();
        br.close();

        int cnt = 0;
        int oN = 0;
        for (int i = 1; i < m - 1; i++) {
            if (read[i - 1] == 'I' && read[i] == 'O' && read[i + 1] == 'I') {
                oN++;
                if (oN == n) {
                    oN--; // 맨 앞의 O가 들어간 건 빼고 다시 세기
                    cnt++;
                }
                i++;
            }
            else oN = 0;
        }
        System.out.println(cnt);
    }
}
