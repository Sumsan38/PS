package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/14500
 * [삼성 SW 역량 테스트 기출 문제]
 * 테트로미노
 * 사람들이 푼 코드 보니까... 그냥....
 * tetromino = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
 * 이런 식으로 19개의 값을 구해서 풀었네;;;;;;;; 내 코드가 느린 것은, 2차원 배열의 1 값을 검사하는 시간이 더 들어가기도 하고,
 * 2차원 배열을 돌리는 2중 포문이 있기도 함
 * */
public class BOJ_14500 {

    static int maxSum = Integer.MIN_VALUE;

    static int n; // 세로 줄 수, 행 정보
    static int m; // 가로 줄 수, 열 정보

    static int[][] map;

    // 완전 탐색으로 0 x 0에서 n x m의 위치까지 테트로미노를 놓고 가장 점수 높은 것을 찾는다
    static ArrayList<int[][]> tetro = new ArrayList<>(); // 총 18개
    static void init(){
        // 1자 모양
        tetro.add(new int[][]{ {1, 1, 1, 1} }); // 가로로 긴것
        tetro.add(new int[][]{ {1}, {1}, {1}, {1} }); // 세로로 긴것

        // 정사각형
        tetro.add(new int[][]{ {1, 1}, {1, 1} }); // 정사각형

        // ㄴ모양 (8개)
        tetro.add(new int[][]{ {1, 0}, {1, 0}, {1, 1}});
        tetro.add(new int[][]{ {0, 0, 1}, {1, 1, 1} });
        tetro.add(new int[][]{ {1, 1}, {0, 1}, {0, 1}});
        tetro.add(new int[][]{ {1, 1, 1}, {1, 0, 0} });

        tetro.add(new int[][]{ {0, 1}, {0, 1}, {1, 1}});
        tetro.add(new int[][]{ {1, 1, 1}, {0, 0, 1} });
        tetro.add(new int[][]{ {1, 1}, {1, 0}, {1, 0}});
        tetro.add(new int[][]{ {1, 0, 0}, {1, 1, 1} });

        // 번개 모양 4개
        tetro.add(new int[][]{ {1, 0}, {1, 1}, {0, 1} });
        tetro.add(new int[][]{ {0, 1, 1}, {1, 1, 0} });
        tetro.add(new int[][]{ {0, 1}, {1, 1}, {1, 0} });
        tetro.add(new int[][]{ {1, 1, 0}, {0, 1, 1} });

        // ㅗ 모양 4개
        tetro.add(new int[][]{ {0, 1, 0}, {1, 1, 1} });
        tetro.add(new int[][]{ {1, 0}, {1, 1}, {1, 0} });
        tetro.add(new int[][]{ {0, 1}, {1, 1}, {0, 1} });
        tetro.add(new int[][]{ {1, 1, 1}, {0, 1, 0} });
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(token.nextToken()); // 세로
        m = Integer.parseInt(token.nextToken()); // 가로

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        init();
        solve();

        System.out.println(maxSum);
    }

    private static void solve(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // 시작점 i행, j열
                // 해당 점에서 테트로미노 19개 모두 넣어본다
                for (int[][] tetroBlock : tetro) {
                    int sum = 0;
                    for (int k = 0; k < tetroBlock.length; k++) {
                        for (int l = 0; l < tetroBlock[k].length; l++) {
                            // 블럭 밖으로 나갔으면 놓을 수 없음. continue
                            if((k + i) > n - 1 || (l + j) > m - 1) continue;

                            if(tetroBlock[k][l] == 1) {
                                sum += map[k + i][l + j];
                            }
                        }
                    }
                    maxSum = Math.max(maxSum, sum);
                }
            }
        }
    }

}
