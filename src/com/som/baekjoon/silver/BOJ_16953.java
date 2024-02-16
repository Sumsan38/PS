package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        long a = Integer.parseInt(token.nextToken());
        long b = Integer.parseInt(token.nextToken()); // Integer보다 작음
        br.close();

        // 연산 거꾸로 돌면서 BFS처럼?
        Queue<Long> list = new LinkedList<>();
        Map<Long, Long> checkMap = new HashMap<>();

        list.add(b);
        checkMap.put(b, 0L);

        while(!list.isEmpty()) {
            long value = list.poll();
            if(value == a || value == 1) break;

            long next = value;
            if(value % 10 == 1) {
                next = value / 10;
                list.add(next);
            } else if(value % 2 == 0) {
                next = value / 2;
                list.add(next);
            } else break;

            if(!checkMap.containsKey(next)) {
                checkMap.put(next, checkMap.get(value) + 1);
            }

        }

        if(checkMap.containsKey(a)) {
            System.out.println(checkMap.get(a) + 1);
        } else {
            System.out.println("-1");
        }

        // 재귀함수 방법 // 참조
        // System.out.println(find((int) a, (int) b, 0));
    }

    // 참조
    public static int find(int a, int b, int cnt) {
        while(true) {
            if(b == a) {
                return cnt + 1;
            }

            if(b < a || (b%2==1 && b%10 >=3 && b%10 <= 9)) {
                // 3부터 9까지의 예외처리
                return -1;
            }

            if(b % 10 == 1) {
                b = b / 10;
                cnt++;
            } else if (b % 2 == 0) {
                b = b / 2;
                cnt++;
            }
        }
    }
}
