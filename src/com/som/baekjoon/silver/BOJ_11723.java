package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_11723  {
    public static void main(String arg[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        int s = 0; // 20개의 꽉찬 상태는 (1<<20) - 1이다
        // (1<<20 - 1) -> 100000000000000000000 - 1 -> 011111111111111111111

        StringTokenizer token;
        while(m-- > 0) {
            token = new StringTokenizer(br.readLine(), " ");
            int x;
            switch (token.nextToken()) {
                case "add" :
                    x = Integer.parseInt(token.nextToken()) - 1;
                    // or 연산자로 켜주기
                    s |= (1<<x);
                    break;
                case "remove" :
                    x = Integer.parseInt(token.nextToken()) - 1;
                    s &= ~(1<<x); // NOT 연산한 것과 & 연산
                    break;
                case "check" :
                    x = Integer.parseInt(token.nextToken()) - 1;
                    // x 값과 or 연산으로 했을때, x 값이 그대로 나오면 존재한다는 뜻
                    if((s & (1<<x)) == (1<<x)) {
                        sb.append("1\n");
                    } else sb.append("0\n");
                    break;
                case "toggle" :
                    x = Integer.parseInt(token.nextToken()) - 1;
                    // 값이 있으면 remove 동작을, 값이 없으면 add 동작을 한다 (xor 연산을 한다)
                    // xor 연산 : 두 값이 다르면 1, 같으면 0을 반환
                    s ^= (1<<x);
                    break;
                case "all" :
                    // 꽉찬 상태로 만들기
                    s = (1<<20) - 1;
                    break;
                case "empty" :
                    // 빈 공집합으로 만들기
                    s = 0;
                    break;
            }

        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    // 그냥 풀은 방법
    /*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        int[] list = new int[21];
        int[] list_all = new int[21];
        for (int j = 1; j <= 20; j++) {
            list_all[j] = 1;
        }
        StringTokenizer token;
        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int x;
            switch (token.nextToken()) {
                case "add" :
                    x = Integer.parseInt(token.nextToken());
                    list[x] = 1;
                    break;
                case "remove" :
                    x = Integer.parseInt(token.nextToken());
                    list[x] = 0;
                    break;
                case "check" :
                    x = Integer.parseInt(token.nextToken());
                    sb.append(list[x]).append("\n");
                    break;
                case "toggle" :
                    x = Integer.parseInt(token.nextToken());
                    if(list[x] == 1) list[x] = 0;
                    else list[x] = 1;
                    break;
                case "all" :
                    list = list_all;
                    break;
                case "empty" :
                    list = new int[3000000];
                    break;
            }
        }

        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    * */
}