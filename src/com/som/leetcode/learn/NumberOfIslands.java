package com.somi.leetcode;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 테스트 방법과 Main.java는 노션을 참조할 것
 */
class NumberOfIslands {
    public int numIslands(char[][] grid) {
    	int island = 0;
    	boolean[][] isVisited = new boolean[grid.length][grid[0].length];
    	
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] == '1' && isVisited[i][j] == false) {
					island++;
					
					Queue<Point> link = new LinkedList<Point>();
					link.offer(new Point(i, j)); // i가 행, j가 열
					
					while(!link.isEmpty()) {
						Point check = link.poll();
						
						int currI = check.i();
						int currJ = check.j();
						if(isVisited[currI][currJ]) continue;
						isVisited[currI][currJ] = true;
						
						// 오른쪽, 왼쪽, 위, 아래 점 1인지 확인해서 queue에 삽입
						int x_left = currJ - 1;
						int x_right = currJ + 1;
						int y_up = currI - 1;
						int y_down = currI + 1;
						
						// 첫째행이 y축, 두번째 행이 x축
						if(x_left >= 0 && grid[currI][x_left] == '1' && isVisited[currI][x_left] == false) link.offer(new Point(currI, x_left));
						if(x_right < grid[0].length && grid[currI][x_right] == '1' && isVisited[currI][x_right] == false) link.offer(new Point(currI, x_right));
						if(y_up >= 0 && grid[y_up][currJ] == '1' && isVisited[y_up][currJ] == false) link.offer(new Point(y_up, currJ));
						if(y_down < grid.length && grid[y_down][currJ] == '1' && isVisited[y_down][currJ] == false) link.offer(new Point(y_down, currJ));
					}
				}
			}
		}
    	return island;
    }
    
    private class Point {
    	int i,j;
    	private Point(int i, int j) {
    		this.i = i;
    		this.j = j;
    	}
    	private int i() { return i; }
    	private int j() { return j; }
    }
}