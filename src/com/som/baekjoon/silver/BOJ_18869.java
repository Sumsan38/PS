package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @see <a href="https://www.acmicpc.net/problem/18869">
 * https://www.acmicpc.net/problem/18869 멀티버스 Ⅱ
 * </a>
 */
public class BOJ_18869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(token.nextToken());
        int N = Integer.parseInt(token.nextToken());

        int[][] beforePoints = new int[M][N];
        for (int i = 0; i < M; i++) {
            StringTokenizer inToken = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                beforePoints[i][j] = Integer.parseInt(inToken.nextToken());
            }
        }
        br.close();

        // 좌표 압축 시작
        // 0부터 N-1까지의 좌표를 갖는다
        for (int i = 0; i < M; i++) {
            beforePoints[i] = returnCompress(beforePoints[i]);
        }

        int pairMulti = 0;
        // 두 포인터를 이용한 쌍 찾기
        int startP = 0;
        int endP = 0;
        while (true) {
            endP = startP + 1;
            if(endP == beforePoints.length) break;
            for (int i = endP; i < beforePoints.length; i++) {
                if(Arrays.equals(beforePoints[startP], beforePoints[i])) {
                    pairMulti++;
                }
            }
            startP++;
        }


        System.out.println(pairMulti);
    }

    public static int[] returnCompress(int[] beforePoint){
        int[] dataList = beforePoint.clone();
        Arrays.sort(dataList);
        HashMap<Integer, Integer> sortHash = new HashMap<>();
        int cnt = 0;
        for (int i: dataList) {
            if(!sortHash.containsKey(i)) {
                sortHash.put(i, cnt++);
            }
        }
        int[] returnList = new int[beforePoint.length];
        for (int i = 0; i < returnList.length; i++) {
            returnList[i] = sortHash.get(beforePoint[i]);
        }
//        int count = 0;
//        int min = 0;
//        while(count < beforePoint.length) {
//            min = dataList[count];
//            int sameV = 0;
//            for (int i = 0; i < beforePoint.length; i++) {
//                if(beforePoint[i] == min) {
//                    returnList[i] = count;
//                    sameV++;
//                }
//            }
//            count = count + sameV;
//        }
        return returnList;
    }
}
