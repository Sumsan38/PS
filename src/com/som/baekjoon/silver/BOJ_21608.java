package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/21608
 * [삼성 SW 역량 테스트 기출 문제]
 * 상어 초등학교
 * */
public class BOJ_21608 {

    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1}; // 상하좌우
    static int[] score = {0, 1, 10, 100, 1000};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] seat = new int[n + 1] [n + 1];

        int[] seatList = new int[n * n + 1]; // 앉힐 순서
        int[][] friend = new int[n * n + 1][n * n + 1]; // [행] -> [열] 친구일 경우 1
        StringTokenizer token;

        // 학생 한명씩 검사 // 모든 자리 검사 // 최고 좋은 자리가 나오면 갱신해줌
        int setSeat = 0;
        for (int i = 1; i <= n * n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int person = Integer.parseInt(token.nextToken());
            seatList[i] = person;
            for (int j = 0; j < 4; j++) { // 4명의 친구 정보 셋
                int friendInfo = Integer.parseInt(token.nextToken());
                friend[person][friendInfo] = 1;
            }
        }


        for (int i = 1; i < friend.length; i++) {
            int person = seatList[i]; // 검사할 학생

            int bestR = Integer.MAX_VALUE; // 최고 자리 행
            int bestC = Integer.MAX_VALUE; // 최고 자리 열

            // 자리마다 아래 두 개 값 갱신하면서 좋은 값으로 // 우선 순위는 인접한 자리 친구의 수이다
            int adjacentFriendCnt = Integer.MIN_VALUE; // 인접한 자리 친구 수
            int blank = Integer.MIN_VALUE; // 주위 빈칸 수

            for (int j = 1; j < seat.length; j++) {
                for (int k = 1; k < seat[j].length; k++) {
                    if(seat[j][k] != 0) continue; // 이미 누가 앉아 있음
                    else if(setSeat == 0) {
                        // 첫번째 사람의 경우 우선순위 법칙에 따라 1,1에 앉는다 (내 소스에서는 N+1이므로 2, 2)
                        bestR = 2;
                        bestC = 2;
                    } else if(setSeat == (n * n) - 1) {
                        // 한 자리만 남았을 경우 그냥 앉힘
                        bestR = j;
                        bestC = k;
                    } else {
                        // 상 하 좌 우 검사 빈칸, 친구여부 검사
                        int checkAdjacentFriendCnt = 0;
                        int checjBlack = 0;
                        for (int l = 0; l < 4; l++) {
                            int dr = j + dx[l];
                            int dc = k + dy[l];
                            if(dr >= 1 && dr <= n && dc >= 1 && dc <= n) {
                                if(seat[dr][dc] == 0) {
                                    checjBlack++; // blank값 ++
                                } else if(friend[person][seat[dr][dc]] == 1) {
                                    checkAdjacentFriendCnt ++; // 친구라면 ++
                                }
                            }
                        }

                        if(checkAdjacentFriendCnt > adjacentFriendCnt) {
                            // 친구 수가 지금까지 찾은 자리보다 더 많다
                            // 0명일 경우에도 일단은 셋팅된다.
                            // 따라서 최악의 자리인 인접 친구 0, 인접 빈자리 0일 경우도 여기 들어감
                            bestR = j;
                            bestC = k;
                            adjacentFriendCnt = checkAdjacentFriendCnt;
                            blank = checjBlack;
                        } else if(checkAdjacentFriendCnt == adjacentFriendCnt) {
                            // 친구 수가 같다. 빈칸이 많은 자리로 해야함
                            if(checjBlack > blank) {
                                bestR = j;
                                bestC = k;
                                adjacentFriendCnt = checkAdjacentFriendCnt;
                                blank = checjBlack;
                            }
                            // 위에서부터 순서대로 올테니, 다음에 찾은 것은 항상 행과 열이 크다
                        }
                    }
                }
            }
            seat[bestR][bestC] = person; // 최고의 자리에 앉힘
            setSeat++;
        }

/*        // 테스트 출력
        System.out.println("------------------------");
        for (int i = 1; i < seat.length; i++) {
            for (int j = 1; j < seat[i].length; j++) {
                System.out.print(seat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");*/


        int totalScore = 0;
        // 여기에 최대 N * N번의 반복문
        for (int i = 1; i < seat.length; i++) {
            for (int j = 1; j < seat[i].length; j++) {
                // 상하좌우 친구 수 검사
                int student = seat[i][j];
                int friendCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int fr = i + dx[k]; // 친구의 위치 (행)
                    int fc = j + dy[k];
                    if(fr >= 1 && fr <= n && fc >= 1 && fc <= n) {
                        int friendNum = seat[fr][fc];
                        if(friend[student][friendNum] == 1) friendCnt++;
                    }
                }
                totalScore += score[friendCnt];
            }
        }
        System.out.println(totalScore);
    }
}