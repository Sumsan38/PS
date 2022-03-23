package com.algospot;

public class Pick {
    // (옵션. 정렬된) 10개의 숫자중에 4개의 숫자를 고르는 재귀함수
    static int n = 10; // [1, 10]
    public static void main(String[] args) {
        int[] list = new int[4];
        int toPick = 0; // 고른 원소의 수

        pick(list, toPick);
    }

    private static void pick(int[] list, int toPick) {
        if(toPick == 4) {
            for (int i: list) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        // 가장 다음으로 가장 작은 값
        int smallest = toPick == 0 ? 0 : list[toPick - 1] + 1;

        for (int next = smallest; next < n; next++) {
            list[toPick] = next;
            pick(list, toPick + 1);
            list[toPick] = 0;
        }
    }
}