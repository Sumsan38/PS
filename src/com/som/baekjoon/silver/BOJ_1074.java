package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(token.nextToken()); // 2^N × 2^N
        int r = Integer.parseInt(token.nextToken());
        int c = Integer.parseInt(token.nextToken()); // r행 c열이 몇 번째 방문한 것인지

        Square square = new Square(0, 0, 0, (int) Math.pow(2, n) - 1, (int) Math.pow(2, n) - 1);
        System.out.println(findSquare(square, r, c));


        br.close();
    }

    static class Square {
        private int[] start;
        private int[] end;
        private long size;
        private int first;

        Square(int first, int startX, int startY, int endX, int endY) {
            this.first = first;
            start = new int[]{startX, startY};
            end = new int[]{endX, endY};
            size = ((endX - startX) + 1) * ((endY - startY) + 1);
        }

        private boolean isContain(int r, int c) {
            // r이 y, c가 x
            if (r >= start[1] && r <= end[1] && c >= start[0] && c <= end[0]) {
                return true;
            } else return false;
        }
    }

    private static int findSquare(Square square, int r, int c) {
        if (square.size == 4) {
            // r행 c열
            int answer = square.first;
            int startR = square.start[1]; // y축 증가 // 행 변경
            int startC = square.start[0]; // x축 증가 // 열 변경
            if(startR == r && startC == c) {
                answer = answer;
            } else if(startR == r && startC + 1 == c) {
                answer = square.first + 1;
            } else if(startR + 1 == r && startC == c) {
                answer = square.first + 2;
            } else if(startR + 1 == r && startC + 1 == c) {
                answer = square.first + 3;
            }
            return answer;
        } else {
            // 4등분해서 각각 square로 만들고, 거기에 isContain 뜨면 재귀함수 호출
            int[] start = square.start;
            int[] end = square.end;
            int middle = (end[0] - start[0]) / 2;
            int first = square.first;
            int multi = (int) (square.size / 4);

            Square checkSquare; // 2 -> 1 -> 3 -> 4 분면 스퀘어로 검사
            checkSquare = new Square(first, start[0], start[1], start[0] + middle, start[1] + middle);
            if (!checkSquare.isContain(r, c)) {
                checkSquare = new Square(first + multi, start[0] + middle + 1, start[1], end[0], start[1] + middle);
                if (!checkSquare.isContain(r, c)) {
                    checkSquare = new Square(first + multi * 2, start[0], start[1] + middle + 1, start[0] + middle, end[1]);
                    if (!checkSquare.isContain(r, c)) {
                        checkSquare = new Square(first + multi * 3, start[0] + middle + 1, start[1] + middle + 1, end[0], end[1]);
                    }
                }
            }
            return findSquare(checkSquare, r, c);
        }
    }
}