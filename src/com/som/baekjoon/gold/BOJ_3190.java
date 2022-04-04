package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/3190
 * [삼성 SW 역량 테스트 기출 문제]
 * 뱀
 * 참조
 * 1) https://leveloper.tistory.com/39
 * 2) https://loosie.tistory.com/269
 * */
public class BOJ_3190 {

    static int n;
    static int[][] map;

    static int apple;
    static int moveCnt;
    static Map<Integer, String> moveInfo;

    // 초기 진행 방향 오른쪽
    // 오른쪽(0), 아래, 왼쪽, 위쪽
    static int d = 0; // 방향 // 오른쪽으로 머리 돌리면 ++
    static int[] dx = {1, 0, -1 , 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        apple = Integer.parseInt(br.readLine());
        for (int i = 0; i < apple; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(token.nextToken()) - 1;
            int c = Integer.parseInt(token.nextToken()) - 1;
            map[r][c] = 1;
        }

        moveCnt = Integer.parseInt(br.readLine());
        moveInfo = new HashMap<>();

        for (int i = 0; i < moveCnt; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(token.nextToken());
            String direction = token.nextToken();
            moveInfo.put(time, direction);
        }
        br.close();

        int time = solution();
        System.out.println(time);
    }

    private static int solution() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int px = 0;
        int py = 0;
        int time = 0;

        while(true) {
            // 우선 가던 방향으로 머리를 뻗는다
            time++;
            int nx = px + dx[d];
            int ny = py + dy[d];

            if(nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) break; // 벽에 머리
            if(q.contains(ny * n + nx)) break; // 몸에 머리

            // 부딪히지 않으면 이동
            if(map[ny][nx] == 1) {
                // 사과가 있음
                map[ny][nx] = 0;
                q.add(ny * n + nx);
            } else {
                q.add(ny * n + nx);
                q.poll(); // 꼬리 제거
            }


            // 방향 전환을 해놓는다
            if(moveInfo.containsKey(time)) {
                String direct = moveInfo.get(time);
                if(direct.equals("D")) { // 오른쪽일 경우 d++;
                    d++;
                    if(d == 4) d = 0;
                } else {
                    d--;
                    if(d == -1) d = 3;
                }
            }
            px = nx;
            py = ny;
        }

        return time;
    }
}
