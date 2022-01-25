package com.baekjoon;

import java.io.*;

public class BOJ_1992 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        char[][] square = new char[n][n];
        for (int i = 0; i < n; i++) {
            String read = br.readLine();
            square[i] = read.toCharArray();
        }
        String answer = compress(square, 0, 0, n-1, n-1);
        if(answer.length() != 1) {
            sb.append("(").append(answer).append(")");
        } else sb.append(answer);
        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static String compress(char[][] square, int startR, int startC, int endR, int endC) {
        StringBuilder sb = new StringBuilder();
        // 2x2 정사각형
        if(endR - startR == 1) {
            int sum = square[startR][startC] + square[startR][endC] + square[endR][startC] + square[endR][endC] - ('0' * 4);
            sb.append(square[startR][startC]).append(square[startR][endC])
                    .append(square[endR][startC]).append(square[endR][endC]);
            String answer = sb.toString();
            if(sum == 4) answer = "1";
            else if(sum == 0) answer = "0";
            else answer = "("+answer+")";
            return answer;
        } else {
            // 4등분 해서 compress로 재호출
            for (int i = 0; i < 4; i++) {
                String check = "";
                switch (i) {
                    case 0:
                        check = compress(square, startR, startC, (startR + endR) / 2, (startC + endC) / 2);
                        break;
                    case 1:
                        check = compress(square, startR, (startC + endC) / 2 + 1, (startR + endR) / 2, endC);
                        break;
                    case 2:
                        check = compress(square, (startR + endR) / 2 + 1, startC, endR, (startC + endC) / 2);
                        break;
                    case 3:
                        check = compress(square, (startR + endR) / 2 + 1, (startC + endC) / 2 + 1, endR, endC);
                        break;
                }
                if(!check.contains("(")) {
                    if(check.equals("1111")) {
                        check = "1";
                    } else if(check.equals("0000")) {
                        check = "0";
                    } else if(check.length() > 1) {
                        check = "(" + check + ")";
                    }
                }
                sb.append(check);
            }
            String answer = sb.toString();

            /*if(!answer.contains("(")) {
                if(answer.equals("1111")) {
                    answer = "1";
                } else if(answer.equals("0000")) {
                    answer = "0";
                }
            } else if(answer.length() > 1) {
                answer = "(" + answer + ")";
            }*/
            
            return answer;
        }
    }
}
