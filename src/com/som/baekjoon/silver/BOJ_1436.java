package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* https://www.acmicpc.net/problem/1436 영화감독 숌 */
public class BOJ_1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        long[] movies = new long[10001];
        long number = 666;
        int series = 1;

        movies[series++] = number;
        while (series <= n) {
            number++;
            if((number+"").contains("666")) { // TODO 이 부분이 바뀌어야 할듯
                movies[series++] = number;
            }
        }

        // System.out.println(movies[n]);
        solve(n);
    }


    // 계산으로 푸는 부분
    // 이해를 돕는 글 https://st-lab.tistory.com/103
    static void solve(int n) {
        int ret;
        int i = 0;
        for(int prev=0;true;prev++) {
            if(prev%10 != 6) {
                i+=1;
                ret = 1000 * prev;
                ret += 666;
                if(i==n) {
                    System.out.println(ret);
                    return;
                }
            }else if(prev%100/10 != 6) {
                for(int post=0;post<10;post++) {
                    i+=1;
                    ret = 1000 * prev;
                    ret += 10 * 66;
                    ret += post;
                    if(i==n) {
                        System.out.println(ret);
                        return;
                    }
                }
            }else if(prev%1000/100 != 6) {
                for(int post=0;post<100;post++) {
                    i+=1;
                    ret = 1000 * prev;
                    ret += 100 * 6;
                    ret += post;
                    if(i==n) {
                        System.out.println(ret);
                        return;
                    }
                }
            }
            else if(prev%10000/1000 != 6) {
                for(int post=0;post<1000;post++) {
                    i+=1;
                    ret = 1000 * prev;
                    ret += post;
                    if(i==n) {
                        System.out.println(ret);
                        return;
                    }
                }
            }
            else if(prev%100000/10000 != 6) {
                for(int post=0;post<10000;post++) {
                    i+=1;
                    ret = 1000 * (prev /10 * 10);
                    ret += post;
                    if(i==n) {
                        System.out.println(ret);
                        return;
                    }
                }
            }

        }
    }
}