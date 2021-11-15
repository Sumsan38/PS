package baekjoon;

import java.io.*;
import java.util.*;
/**
 * @see <a href="https://www.acmicpc.net/problem/2659">
 * https://www.acmicpc.net/problem/2659 십자카드 문제
 * </a>
 */

public class BOJ_2659 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String read = br.readLine();
		br.close();
		
		StringTokenizer token = new StringTokenizer(read, " ");
		int[] cross = new int[4]; 
		for (int i = 0; i < cross.length; i++) {
			cross[i] = Integer.parseInt(token.nextToken());
		}
		int min = getMinNum(cross);
		
		ArrayList<Integer> fillList = new ArrayList<Integer>();
		// 모든 시계수 배열
		int[] answer = new int[4];
		for (int i = 1; i < 10; i++) {
			answer[0] = i;
			for (int j = 1; j < 10; j++) {
				answer[1] = j;
				for (int k = 1; k < 10; k++) {
					answer[2] = k;
					for (int l = 1; l < 10; l++) {
						answer[3] = l;
						int sum = getMinNum(answer);
						if(!fillList.contains(sum)) fillList.add(sum);
					}
				}
			}
		}
		
		bw.write(fillList.indexOf(min) + 1 + "");
		bw.flush();
		bw.close();
	}
	
	
	private static int getMinNum(int list[]) {
		int min = list[0]*1000 + list[1]*100 + list[2]*10 + list[3];
		for (int i = 0; i < 4; i++) {
			int sum = (list[i] * 1000) + (list[(i + 1) % 4] * 100) + (list[(i + 2) % 4] * 10) + list[(i + 3) % 4];
			min = sum < min? sum : min;
		}
		return min;
	}
}
