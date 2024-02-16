package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/14888
 * [삼성 SW 역량 테스트 기출 문제]
 * 연산자 끼워넣기
 * */
public class BOJ_14888 {

    static int n;
    static int[] number;
    static int[] operator;
    static int[] operatorList; // 0 덧셈, 1 뺄셈, 2 곱셈, 3 나눗셈

    static int OPERATOR_CNT = 4;
    static int maxAnwer = Integer.MIN_VALUE;
    static int minAnswer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n =Integer.parseInt(br.readLine());
        number = new int[n];
        operator = new int[OPERATOR_CNT]; // 덧셈, 뺄셈, 곱셈, 나눗셈
        operatorList = new int[n - 1];

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(token.nextToken());
        }
        token = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < OPERATOR_CNT; i++) {
            operator[i] = Integer.parseInt(token.nextToken());
        }
        br.close();

        findAnswer(0);
        sb.append(maxAnwer).append("\n");
        sb.append(minAnswer).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void findAnswer(int cnt) {
        // 연산자를 n-1개 골랐다면 값을 계산한다
        if(cnt == n - 1) {
            int value = number[0];
            for (int i = 0; i < cnt; i++) {
                switch (operatorList[i]) {
                    case 0:
                        // 덧셈
                        value = value + number[i + 1];
                        break;
                    case 1:
                        // 뺄셈
                        value = value - number[i + 1];
                        break;
                    case 2:
                        // 곱셈
                        value = value * number[i + 1];
                        break;
                    case 3:
                        // 나눗셈 // 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다
                        if(value < 0) {
                            value = (Math.abs(value) / number[i + 1]) * -1;
                        } else {
                            value = value / number[i + 1];
                        }
                        break;
                }
            }
            maxAnwer = Math.max(maxAnwer, value);
            minAnswer = Math.min(minAnswer, value);
        }

        // 연산자 4개 중에 n-1개를 고르는 재귀
        for (int i = 0; i < 4; i++) {
            // 4개 중 하나를 고른다
            if(operator[i] >= 1) {
                operator[i] = operator[i] - 1;
                operatorList[cnt] = i;
                findAnswer(cnt + 1);

                // 이것도 백트레킹 // 선택하지 않은 것으로 되돌린다
                operator[i] = operator[i] + 1;
            }
        }

    }
}
