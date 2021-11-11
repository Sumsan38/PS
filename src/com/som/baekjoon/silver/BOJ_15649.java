package baekjoon;

import java.io.*;
import java.util.*;
/**
 * @see <a href="https://www.acmicpc.net/problem/15649">
 * https://www.acmicpc.net/problem/15649 N과 M (1)
 * 백트레킹은 기존의 브로트포스와 DFS와 혼동 될 수 있다
 */
public class BOJ_15649 {
	static int N;
	static int M;
	static int[] array;
	static boolean[] isVisited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(token.nextToken()); // 자연수
		M = Integer.parseInt(token.nextToken()); // 배열 깊이
		br.close();
		
		array = new int[M]; // 출력 Array
		isVisited = new boolean[N]; // 방문
		dfs(0);
		
		bw.write(sb.toString());
		bw.close();
	}
	
	private static void dfs(int depth) { // 현재 깊이
		// 깊이가 M에 도달했다면 return
		if(depth == M) {
			for (int i : array) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 그게 아니라면 탐색을 잇는다
		for (int i = 0; i < N; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				array[depth] = i + 1;
				dfs(depth + 1);
				isVisited[i] = false; // 모든 탐색이 끝났을 때는 현재 점을 방문하지 않은 것으로 돌린다
			}
		}
	}
}
