package com.somi.programmers;

import java.util.ArrayList;
import java.util.List;

public class Hanoi {
    // [하노이의 탑] https://school.programmers.co.kr/learn/courses/30/lessons/12946

    public int[][] solution(int n) {
        List<int[]> moveList = new ArrayList<>();
        int startPole = 1;
        int middlePole = 2;
        int endPole = 3;
        moveStencil(n, startPole, endPole, middlePole, moveList);

        return moveList.toArray(int[][]::new);
    }

    private void moveStencil(int n,
                             int startingPole,
                             int destinationPole,
                             int tempPole,
                             List<int[]> moveList) {
        // 더 작은 원판이 없다면 return
        if(n == 0) return;

        // n보다 작은 원판을 미리 tempPole 옮겨 놓는다.
        moveStencil(n - 1, startingPole, tempPole, destinationPole, moveList);

        // 그 뒤에 n 원판 실제 이동
//        System.out.println("n: " + n + ", move: [" + startingPole + "," + destinationPole + "]");
        moveList.add(new int[]{startingPole, destinationPole});

        // n보다 작은 원판을 다시 목표 위로 올려놓는다
        moveStencil(n - 1, tempPole, destinationPole, startingPole, moveList);
    }


//    private void printSolution(int n, int[][] solution){
//        System.out.println("Start Hanoi: " + n);
//        for (int[] ints : solution) {
//            System.out.print("[" + ints[0] + "," + ints[1] + "] ");
//        }
//        System.out.println("\nEnd");
//    }
//
//    private void move(int n, int start, int destination, int temp){
//        if(n == 0) return;
//
//        // 나보다 작은 원판 temp로 이동
//        move(n - 1, start, temp, destination);
//
//        // 그뒤에 n 이동
//        System.out.println(n + "원판 이동, " + start + " -> " + destination);
//
//        // 나보다 작은 원판 temp에서 destination으로 이동
//        move(n - 1, temp, destination, start);
//    }
}
