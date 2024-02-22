package com.somi.programmers;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OilDrilling {

    static class OilMax{
        private int oil;
        OilMax() {
            this.oil = 0;
        }
        private void findNewOil(){
            oil++;
        }
    }
    int[][] moveCursor = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 순서
    public int solution(int[][] land) {
        // 방법 1. 기존의 dfs, bfs (timeOut)
//        int answer = 0;
//        for (int colum = 0; colum < land[0].length; colum++) {
//            boolean[][] afterWork = new boolean[land.length][land[0].length];
//            int getOil = 0;
//            for (int row = 0; row < land.length; row++) {
//                // 석유 발견
//                if(land[row][colum] == 1 && ! afterWork[row][colum]) {
//                    // dfs
//                    getOil += drillingOilDfs(land, afterWork, row, colum, 0);
//                    // bfs
//                    getOil += drillingOilBfs(land, afterWork, row, colum, 0);
//                }
//            }
//            answer = Math.max(answer, getOil);
//        }

        // 방법 2
        // 2중 포문 한번만 돌면서 연결된 석유 덩어리를 특정 객체에 넣어둠
        int answer = 0;
        OilMax[][] oilTable = new OilMax[land.length][land[0].length]; // null = 방문하지 않은 것
        for (int row = 0; row < land.length; row++) {
            for (int colum = 0; colum < land[0].length; colum++) {
                if(land[row][colum] == 1 && oilTable[row][colum] == null){
                    drillingOilBfs(land, oilTable, row, colum, new OilMax());
                }
            }
        }

        // 계산
        Set<OilMax> checkSet;
        for (int colum = 0; colum < land[0].length; colum++) {
            checkSet = new HashSet<>();
            int sum = 0;
            for (int row = 0; row < land.length; row++) {
                if(oilTable[row][colum] != null) {
                    OilMax oil = oilTable[row][colum];
                    if(! checkSet.contains(oil)) {
                        sum += oil.oil;
                        checkSet.add(oil);
                    }
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    private void drillingOilBfs(int[][] land, OilMax[][] oilTable, int row, int colum, OilMax oilMax){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, colum});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int getRow = point[0];
            int getColum = point[1];
            if (oilTable[getRow][getColum] != null) continue;

            oilMax.findNewOil();
            oilTable[getRow][getColum] = oilMax;

            for (int[] move : moveCursor) {
                int moveRow = getRow + move[0];
                int moveColum = getColum + move[1];
                if (!(moveRow >= 0 && moveRow < land.length)) continue;
                if (!(moveColum >= 0 && moveColum < land[0].length)) continue;

                if (oilTable[moveRow][moveColum] == null && land[moveRow][moveColum] == 1) {
                    queue.add(new int[]{moveRow, moveColum});
                }
            }
        }
    }

    /*
    // dfs
    private int drillingOilDfs(int[][] land, boolean[][] afterWork, int row, int colum, int getOil){
        if(afterWork[row][colum]) {
            return getOil;
        }

        // 탐색하면서 이미 먹은 땅엔 afterWork[][]을 1으로 업데이트
        getOil++;
        afterWork[row][colum] = true;

        // 상하좌우로 시추 시작
        for (int[] move : moveCursor) {
            int moveRow = row + move[0];
            int moveColum = colum + move[1];
            if(!(moveRow >= 0 && moveRow < land.length)) continue;
            if(!(moveColum >= 0 && moveColum < land[0].length)) continue;

            if(land[moveRow][moveColum] == 1 && ! afterWork[moveRow][moveColum]) {
                getOil = drillingOilDfs(land, afterWork, moveRow, moveColum, getOil);
            }
        }

        return getOil;
    }

    // bfs
    private int drillingOilBfs(int[][] land, boolean[][] afterWork, int row, int colum, int getOil){
        if(land[row][colum] == 1 && ! afterWork[row][colum]) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{row, colum});
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int getRow = point[0];
                int getColum = point[1];
                if (afterWork[getRow][getColum]) continue;

                getOil++;
                afterWork[getRow][getColum] = true;

                for (int[] move : moveCursor) {
                    int moveRow = getRow + move[0];
                    int moveColum = getColum + move[1];
                    if (!(moveRow >= 0 && moveRow < land.length)) continue;
                    if (!(moveColum >= 0 && moveColum < land[0].length)) continue;

                    if (!afterWork[moveRow][moveColum] && land[moveRow][moveColum] == 1) {
                        queue.add(new int[]{moveRow, moveColum});
                    }
                }
            }
        }
        return getOil;
    }
    */

}
