package baekjoon;

import java.io.*;
import java.util.*;

/**
 * @see <a href="https://www.acmicpc.net/problem/1260">
 * https://www.acmicpc.net/problem/1260
 * </a> DFS와 BFS
 */
public class BOJ_1260 {
	
	static boolean[] isVisited; // DFS
	static Queue<Integer> bfsQueue; // BFS
	
	static ArrayList<ArrayList<Integer>> graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(token.nextToken()); // 정점의 개수 N(1 ≤ N ≤ 1,000), 
		int M = Integer.parseInt(token.nextToken()); // 간선의 개수 M(1 ≤ M ≤ 10,000), 
		int V = Integer.parseInt(token.nextToken()); // 탐색을 시작할 정점의 번호 V
		
		bfsQueue = new LinkedList<Integer>();
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			String read = br.readLine();
			int start = Integer.parseInt(read.substring(0, read.indexOf(" ")));
			int end = Integer.parseInt(read.substring(read.indexOf(" ") + 1, read.length()));
			graph.get(start).add(end);
			if(!graph.get(end).contains(start))
				graph.get(end).add(start);
			// 서로 연결되어 있어야하는데?
		}
		br.close();
		
		// 오름차순 정렬 // 필요없나? // 문제가 그냥 오름차순으로 주진 않을듯
		for (int i = 1; i < N + 1; i++) {
			Collections.sort(graph.get(i));
		}
		
		isVisited = new boolean[N + 1];
		dfs(V);
		System.out.println();

		isVisited = new boolean[N + 1];
		explorerUsingBFS(V);
	}
	
	// 재귀를 통한 DFS
	private static void dfs(int V) {
		isVisited[V] = true; // 방문
		System.out.print(V + " ");
		for (int i = 0; i < graph.get(V).size(); i++) {
			int next = graph.get(V).get(i);
			if(!isVisited[next]) {
				dfs(next);
			}
		}
	}
	
	
	private static void explorerUsingBFS(int V) {
		// 시작
		isVisited[V] = true;
		bfsQueue.offer(V);
		
		while (!bfsQueue.isEmpty()) {
			int index = bfsQueue.poll();
			System.out.print(index + " ");
			for (int i = 0; i < graph.get(index).size(); i++) {
				int next = graph.get(index).get(i);
				// 방문하지 않았다면 큐에 삽입
				if(!isVisited[next]) {
					isVisited[next] = true;
					bfsQueue.offer(graph.get(index).get(i));
				}
			}
			
		}
	}
}
