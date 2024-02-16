package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/1068">
 * https://www.acmicpc.net/problem/1068 트리
 * </a> 트리
 */
public class BOJ_1068 {
	static int N = 0;
	static int startPoint = 0;
	static int[] parent;
	static boolean[] visited;
	static int cnt = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");
        int removeNode = Integer.parseInt(br.readLine());
        
        br.close();
        
        parent = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < parent.length; i++) {
        	int paernt = Integer.parseInt(token.nextToken());
			parent[i] = paernt;
			if(paernt == -1) startPoint = i;
		}
        
        // removeNode부터 그 아래 전부 remove
        deleteNode(removeNode);
        findLeefNode(startPoint);
        
        
        System.out.println(cnt);
    }
    
    public static void deleteNode(int i) {
    	parent[i] = -2; // 삭제되는 곳은 -2
    	for (int j = 0; j < parent.length; j++) {
    		if(parent[j] == i) {
    			deleteNode(j);
    		}
		}
    }
    
    public static void findLeefNode(int explNum) {
    	boolean isLeef = true;
    	visited[explNum] = true;
    	if(parent[explNum] != -2) {
    		for (int i = 0; i < N; i++) {
    			if(parent[i] == explNum && visited[i] == false) {
    				findLeefNode(i);
    				isLeef = false;
    			}
    		}
    		if(isLeef) cnt++; // 이거를 생각하지 못했다
    	}
    }
}
