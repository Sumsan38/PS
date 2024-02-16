package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/14891
 * [삼성 SW 역량 테스트 기출 문제]
 * 톱니바퀴
 * 풀이 시간 : 16:28 ~ 17:46
 * */
public class BOJ_14891 {
    static int[][] list = new int[5][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String read = br.readLine();
            int[] in = new int[8];
            for (int j = 0; j < 8; j++) {
                in[j] = read.charAt(j) - '0';
            }
            list[i + 1] = in;
        }

        // 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향
        int n = Integer.parseInt(br.readLine());
        StringTokenizer token;
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(token.nextToken());
            int direct = Integer.parseInt(token.nextToken());

            rotation(0, num, direct);
        }

        System.out.println(getTotalScore());
    }

    // 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향
    private static void rotation(int beforeNum, int num, int direct){
        int[] me = list[num];
        // 톱니바퀴 A를 회전할 때, 그 옆에 있는 톱니바퀴 B와 서로 맞닿은 톱니의 극이 다르다면,
        // B는 A가 회전한 방향과 반대방향으로 회전

        boolean leftRotation = false;
        boolean rightRotation = false;

        if(num >= 2 && (num - 1 != beforeNum)) {
            int[] left = list[num - 1]; // 2, 3, 4일 경우 왼쪽이 있음
            // 본인의 톱니 [6]번과 왼쪽 톱니 [2]번이 같은지 다른지 검사
            if(me[6] != left[2]) leftRotation = true;
        }

        if(num <= 3 && (num + 1 != beforeNum)) {
            int[] right = list[num + 1]; // 1, 2, 3일 경우 오른쪽이 있음
            if(me[2] != right[6]) rightRotation = true;
        }

        // 본인꺼 회전
        int[] out = new int[8];
        for (int i = 0; i <= 7; i++) {
            if(i + direct < 0 || i + direct >= out.length) continue;
            out[i + direct] = me[i];
        }
        if(direct == 1)
            out[0] = me[7];
        else out[7] = me[0];
        list[num] = out;

        if(leftRotation) rotation(num, num - 1, direct * -1);
        if(rightRotation) rotation(num, num + 1, direct * -1);
    }

    /*
    // N극은 0, S극은 1
    1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
    2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
    3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
    4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
     */
    private static int getTotalScore(){
        int sum = 0;
        sum += (list[1][0] == 0? 0 : 1);
        sum += (list[2][0] == 0? 0 : 2);
        sum += (list[3][0] == 0? 0 : 4);
        sum += (list[4][0] == 0? 0 : 8);
        return sum;
    }
}
