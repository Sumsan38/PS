package com.som.programmers.level2;

public class TriangleSnail {
    // [삼각 달팽이] https://school.programmers.co.kr/learn/courses/30/lessons/68645

    public static void main(String[] args) {
        System.out.println(printArray(solution(1)));
        //4	[1,2,9,3,10,8,4,5,6,7]
        System.out.println(printArray(solution(4)));
        //5	[1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
        System.out.println(printArray(solution(5)));
        //6	[1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
        System.out.println(printArray(solution(6)));
        // 추가
        System.out.println(printArray(solution(7)));
    }

    // 삼각형 순서대로 이동 방향
    private static final int[][] moves = new int[][]{{0, 1}, {1, 0}, {-1, -1}};
    public static int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int num = 1;
        int row = 0, height = -1;
        for (int i = 0; i < n; i++) {
            int[] move = moves[i % 3];  // 이번에 움직일 방향

            while(true) {
                row = row + move[0];
                height = height + move[1];

                if( !(row >= 0 && row < n && height >= 0 && height < n) || triangle[height][row] != 0) {
                    row = row - move[0];
                    height = height - move[1];
                    break;
                } else {
                    triangle[height][row] = num++;
                }
            }
        }

        int[] answer = new int[num - 1];
        int answerCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int getValue = triangle[i][j];
                if(getValue == 0) break;
                answer[answerCnt++] = getValue;
            }
        }

        return answer;
    }

    // 프린트용
    private static String printArray(int[] answer){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
