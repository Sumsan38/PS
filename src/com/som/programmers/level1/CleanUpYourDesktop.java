package com.som.programmers.easy;

public class CleanUpYourDesktop {
    // [바탕화면 정리] https://school.programmers.co.kr/learn/courses/30/lessons/161990

    public int[] solution(String[] wallpaper) {
        int xLength = wallpaper.length;
        int yLength = wallpaper[0].length();

        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = Integer.MIN_VALUE;
        int endY = Integer.MIN_VALUE;

        // 찾기
        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                char getFile = wallpaper[x].charAt(y);
                if(getFile == '#') {
                    startX = Math.min(startX, x);
                    endX = Math.max(endX, x);
                    startY = Math.min(startY, y);
                    endY = Math.max(endY, y);
                }
            }
        }

        return new int[]{startX, startY, endX + 1, endY + 1};
    }
}
