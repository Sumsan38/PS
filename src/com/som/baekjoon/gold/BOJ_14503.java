package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/14503
 * [삼성 SW 역량 테스트 기출 문제]
 * 로봇 청소기
 * 풀이 시간 : 20:27 ~ 21:35
 * */
public class BOJ_14503 {

    static int d; // 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽
    // 각 방향(d)에서 전진하는 경우 // 후진 하는 경우는 -1을 곱해준다
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int n;
    static int m;

    static int[] current;
    static int[][] floor;

    static int cleanCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(token.nextToken()); // 행
        m = Integer.parseInt(token.nextToken()); // 열
        floor = new int[n][m];

        token = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(token.nextToken());
        int c = Integer.parseInt(token.nextToken());
        current = new int[]{r, c};
        d = Integer.parseInt(token.nextToken());

        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                floor[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        clean();
        System.out.println(cleanCnt);
    }

    private static void clean(){
        if(floor[current[0]][current[1]] == 0) {
            floor[current[0]][current[1]] = 2;
            cleanCnt++;
        }

        // 왼쪽으로 회전한 다음 앞칸이 바닥이면 (0)이면 청소기 이동
        // 해당 방향으로 d 셋팅
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;

            int frontR = current[0] + dy[d];
            int frontC = current[1] + dx[d];

            if((frontR >= 0 && frontR < n && frontC >= 0 && frontC < m)) {
                if(floor[frontR][frontC] == 0) {
                    current[0] = frontR; // 여기 때문에 스택오버 플로우 남 ㅡㅡ 변수를 안받을거면 셋팅이라도 제대로 해주라고!!!!!!
                    current[1] = frontC;
                    clean();
                    flag = true;
                    break;
                }
            }
        }

        // 네 방향이 모두 청소가 되어있거나 벽인 경우?
        if(!flag) {
            // 4 방향을 다 회전했을 때 여기로 온다
            int backR = current[0] + (dy[d] * -1);
            int backC = current[1] + (dx[d] * -1);

            // 모든 벽에 닿거나, 후진했을때 벽일 경우 종료
            if(backR >= 0 && backR < n && backC >= 0 && backC < m) {
                if(floor[backR][backC] != 1) {
                    // 후진해서 다시 청소 시작
                    current[0] = backR;
                    current[1] = backC;
                    clean();
                }
            }
        }
    }

}
