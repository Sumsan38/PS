package baekjoon;

import java.io.*;
import java.util.*;
/**
 * @see <a href="https://www.acmicpc.net/problem/2606">
 * https://www.acmicpc.net/problem/2606
 * [백준, 2606] 바이러스
 * </a>
 */
public class BOJ_2606 {
	
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int computer = Integer.parseInt(br.readLine());
		int connect = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] list = new ArrayList[computer + 1];
		for (int i = 1; i < computer + 1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		boolean[] isVisited = new boolean[computer + 1];
		
		for (int i = 0; i < connect; i++) {
			String read = br.readLine();
			int start = Integer.parseInt(read.substring(0, read.indexOf(" ")));
			int end = Integer.parseInt(read.substring(read.indexOf(" ") + 1, read.length()));
			
			// 서로 연결
			list[start].add(end);
			list[end].add(start);
		}
		br.close();
		
//		dfs(1, list, isVisited);
		bfs(1, list, isVisited);
		answer = answer > 0 ? answer - 1 : answer;
		System.out.println(answer);
	}
	
	static void dfs(int value, ArrayList<Integer>[] list, boolean[] isVisited) {
		isVisited[value] = true;
		answer++;
		for (int i = 0; i < list[value].size(); i++) {
			int next = list[value].get(i);
			if(!isVisited[next]) {
				dfs(next, list, isVisited);
			}
		}
	}
	
	static void bfs(int value,  ArrayList<Integer>[] list, boolean[] isVisited) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		isVisited[value] = true;
		answer++;
		queue.offer(value);
		
		while(!queue.isEmpty()) {
			int popValue = queue.pop();
			for (int i = 0; i < list[popValue].size(); i++) {
				int next = list[popValue].get(i);
				if(!isVisited[next]) {
					queue.offer(next);
					isVisited[next] = true;
					answer++;
				}
			}
		}
	}
	
}
