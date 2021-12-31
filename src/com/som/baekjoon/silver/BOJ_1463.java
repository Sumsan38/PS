package com.baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        br.close();

        list = new Integer[x + 1];
        list[0] = list[1] = 0;
        System.out.println(get(x));
    }

    static Integer[] list; // 최소 값을 기록할 list // 메모이제이션
    public static int get(int input) {
        if(list[input] == null) {
            if(input % 6 == 0) {
                // 세 가지 경우에서 비교해야함
                list[input] = Math.min(get(input - 1), Math.min(get(input / 3), get(input / 2))) + 1;
            } else if(input % 3 == 0) {
                list[input] = Math.min(get(input / 3), get(input - 1)) + 1;
            } else if(input % 2 == 0) {
                list[input] = Math.min(get(input / 2), get(input - 1)) + 1;
            } else {
                // 2와 3으로 나누어 떨어지지 않을 때
                list[input] = get(input - 1) + 1;
            }
        }
        return list[input];
    }

    // BFS로 푸는 방법도 있다.
    // input에 대해 / 3, / 2, -1로 만들어진 숫자를 전부 queue에 넣고,
    // array에 해당 숫자의 cnt 값을 넣는다.
    // input이 2,3에 도달했을 때 cnt 값을 리턴하는 식으로..
}
