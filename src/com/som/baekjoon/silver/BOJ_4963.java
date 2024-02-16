package com.somi.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/4963
 * 섬의 개수
 */
public class BOJ_4963 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			String read = br.readLine();
			if(read.equals("0 0")) break;
			
			StringTokenizer token = new StringTokenizer(read, " ");
			int w = Integer.parseInt(token.nextToken());
			int h = Integer.parseInt(token.nextToken());
			char[][] all = new char[h][w];
			for (int i = 0; i < h; i++) {
				token = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					all[i][j] = token.nextToken().charAt(0);
				}
			}
			sb.append(findNmbIsland(all));
			sb.append("\n");
		}
		br.close();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int findNmbIsland(char[][] all){
		int island = 0;
		int w = all[0].length;
		int h = all.length;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(all[i][j] == '1') {
					island++;
					checkIslandTwoWay(all, j, i);
				}
			}
		}
		return island;
	}
	
	private static void checkIslandTwoWay(char[][] all, int j, int i) {
		int w = all[0].length;
		int h = all.length;
		
		if(i >= 0 && j >= 0 && i < h && j < w && all[i][j] == '1') {
			all[i][j] = '0';
			checkIslandTwoWay(all, j, i - 1); // 위
			checkIslandTwoWay(all, j, i + 1); // 아래
			checkIslandTwoWay(all, j - 1, i); // 왼쪽
			checkIslandTwoWay(all, j + 1, i);// 오른쪽
			
			// 대각선 추가 검사
			checkIslandTwoWay(all, j - 1, i - 1); // 좌상단
			checkIslandTwoWay(all, j - 1, i + 1); // 좌하단
			checkIslandTwoWay(all, j + 1, i + 1); // 우상단
			checkIslandTwoWay(all, j + 1, i - 1); // 우하단
		}
	}
}
