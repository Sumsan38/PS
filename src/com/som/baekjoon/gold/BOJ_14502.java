package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/14502
 * [삼성 SW 역량 테스트 기출 문제]
 * 연구소
 * 풀이 시간 : 15:00 ~ 16:26
 * */
public class BOJ_14502 {

    static int n; // 행
    static int m; // 열
    static int[][] map; // 연구소 기본 정보

    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int safeZone = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        setThreeWall(0);
        System.out.println(safeZone);
    }

    // 세 개의 벽을 랜덤으로 세우는 모든 방법 // 재귀함수
    private static void setThreeWall(int wallCnt) {
        if(wallCnt == 3) {
            // 벽이 세개 세워지면 안전 지대 세기
            checkSafeZone();
            return;
        }

        // 수많은 자리중에 3개를 뽑는것
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    setThreeWall(wallCnt + 1);

                    // 백 트레킹
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void checkSafeZone(){
        int safeZoneCnt = 0;

        int[][] mapCheck = new int[n][m];
        for (int i = 0; i < n; i++) {
            // map 복사
            for (int j = 0; j < m; j++) {
                mapCheck[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < mapCheck.length; i++) {
            for (int j = 0; j < mapCheck[i].length; j++) {
                if(mapCheck[i][j] == 2) {
                    fillVirus(mapCheck, i, j);
                }
            }
        }

        // 0의 갯수 세기
        for (int i = 0; i < mapCheck.length; i++) {
            for (int j = 0; j < mapCheck[i].length; j++) {
                if(mapCheck[i][j] == 0) safeZoneCnt++;
            }
        }
        safeZone = Math.max(safeZoneCnt, safeZone);


        /*
        // TODO DELETE 테스트용 벽 채워진거 출력
        System.out.println("----------------------------");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
        // TODO DELETE 바이러스 채워진거 출력
        for (int i = 0; i < mapCheck.length; i++) {
            for (int j = 0; j < mapCheck[i].length; j++) {
                System.out.print(mapCheck[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Check :: " + safeZone);
        System.out.println("----------------------------");
        */

    }

    private static void fillVirus(int[][] map, int r, int c) {
        int h = map.length;
        int w = map[0].length;

        // 8방향 채워주기
        for (int i = 0; i < 4; i++) {
            int nr = r + dy[i];
            int nc = c + dx[i];
            if(nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] == 0) {
                map[nr][nc] = 2;
                fillVirus(map, nr, nc);
            }
        }
    }
}