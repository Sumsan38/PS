package com.baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/1920 수 찾기 */
public class BOJ_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        /* 이분 탐색으로 푸는 방법 */
        int n = Integer.parseInt(br.readLine());
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int[] alist = new int[n];
        for (int i = 0; i < n; i++) {
            alist[i] = Integer.parseInt(token.nextToken());
        }
        Arrays.sort(alist);

        int m = Integer.parseInt(br.readLine());
        token = new StringTokenizer(br.readLine(), " ");
        br.close();

        while(token.hasMoreTokens()){
            int check = Integer.parseInt(token.nextToken());
            int value = get(alist, check);
            if(value > 0) sb.append("1\n");
            else sb.append("0\n");
        }


        /* 해쉬맵으로 푸는 방법 */
        /*
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> aMap = new HashMap<Integer, Integer>();
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            aMap.put(Integer.parseInt(token.nextToken()), 1);
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        StringTokenizer mToken = new StringTokenizer(br.readLine(), " ");
        br.close();

        for (int i = 0; i < m; i++) {
            int check = Integer.parseInt(mToken.nextToken());
            if(aMap.containsKey(check)) sb.append(aMap.get(check)).append("\n");
            else sb.append("0").append("\n");
        }
        */


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int get(int[] alist, int check){
        int start = 0;
        int end = alist.length - 1;
        int mid = start;

        while(start <= end) {
            mid = (start + end) / 2;
            if(alist[mid] > check) { // 중간 값이 check보다 큼 (end를 땡겨 왼쪽 탐색)
                end = mid - 1;
            } else if(alist[mid] < check) { // 중간 값이 check보다 작음 (start를 떙겨 오른쪽 탐색)
                start = mid + 1;
            } else {
                return 1;
            }
        }

        return -1;
    }
}
