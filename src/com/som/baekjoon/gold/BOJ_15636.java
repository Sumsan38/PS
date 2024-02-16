package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/15636
 * [삼성 SW 역량 테스트 기출 문제]
 * 치킨 배달
 * 풀이 시간 : 09:30 ~ 10:19
 * */
public class BOJ_15636 {

    static int[][] city;
    static int m;
    static int n;

    static int totalCheckin;

    static List<Integer> house;
    static Map<Integer, Integer> chicken;

    static int minChicken = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(token.nextToken()); // 도시의 행, 열
        m = Integer.parseInt(token.nextToken()); // 페업시키지 않을 치킨집

        totalCheckin = 0; // 총 치킨집 갯수

        house = new ArrayList<>(); // 집의 정보
        chicken = new LinkedHashMap<>(); // 치킨 집의 정보

        city = new int[n][n];
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int v = Integer.parseInt(token.nextToken());
                city[i][j] = v;
                if(v == 1) {
                    house.add(i * n + j);
                } else if(v == 2) {
                    chicken.put(i * n + j, 1);
                    totalCheckin++;
                }
            }
        }

        chooseClose(0, 0);
        System.out.println(minChicken);
    }


    private static void chooseClose(int choose, int beforelocation){
        if((totalCheckin - choose) == m) {
            // 집과 치킨집들 치킨 거리 구하는 방법
            updateChichenLoad();
            return;
        }

        for (int location : chicken.keySet()) {
            // 이미 전에서 폐업한다고 골라짐
            if(chicken.get(location) == 0 || location < beforelocation) continue;

            int r = location / n; // 행
            int c = location % n; // 열

            // 폐업
            city[r][c] = 0;
            chicken.put(location, 0);
            chooseClose(choose + 1, location);

            // 백트래킹 사용 // 폐업하지 않은 것으로 되돌림
            chicken.put(location, 1);
            city[r][c] = 2;
        }
    }

    private static void updateChichenLoad (){
        int loadSum = 0;
        for (int i = 0; i < house.size(); i++) {
            // 각 집에서 폐업하지 않은 치킨집과의 거리 최소
            int houseIloadSum = Integer.MAX_VALUE;
            int hr = house.get(i) / n;
            int hc = house.get(i) % n;
            for (int location : chicken.keySet()) {
                int isClosed = chicken.get(location);
                if(isClosed == 0) continue; // 폐업한 치킨집
                int cr = location / n;
                int cc = location % n;

                // 임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|
                int newloadSum = Math.abs(hr - cr) + Math.abs(hc - cc);
                houseIloadSum = Math.min(houseIloadSum, newloadSum);
            }
            loadSum += houseIloadSum;
        }
        minChicken = Math.min(minChicken, loadSum);
    }
}
