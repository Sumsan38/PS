package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/13460
 * [삼성 SW 역량 테스트 기출 문제]
 * 스타트와 링크
 * */
public class BOJ_14889 {

    static int n;
    static boolean[] isSelected;
    static int[][] ability;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ability = new int[n + 1][n + 1];
        StringTokenizer token;
        for (int i = 1; i <= n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                ability[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        br.close();

        isSelected = new boolean[n + 1];
        getMinScore(1, 0);
        System.out.println(answer);
    }

    private static void getMinScore(int start, int cnt) {
        // 만약 n/2명을 골랐다면, 안골라진 나머지 사람들 합 구하기
        if(cnt == n / 2) {
            int scoreTeam = 0;
            int scoreOtherTeam = 0;

            for (int i = 1; i < ability.length; i++) {
                for (int j = i + 1; j <ability[i].length ; j++) {
                    if(isSelected[i] && isSelected[j]) {
                        scoreTeam += ability[i][j];
                        scoreTeam += ability[j][i];
                    } else if(!isSelected[i] && !isSelected[j]) {
                        scoreOtherTeam += ability[i][j];
                        scoreOtherTeam += ability[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(scoreOtherTeam - scoreTeam));
        }

        // 선택되지 않은 사람을 골라 점수를 더하는 과정
        for (int i = start; i <= n; i++) {
            if(!isSelected[i]) {
                isSelected[i] = true;
                getMinScore(i + 1,cnt + 1);
                isSelected[i] = false; // 모든 탐색을 마친 뒤에 선택되지 않은 것으로 되돌림
            }
        }
    }
}
