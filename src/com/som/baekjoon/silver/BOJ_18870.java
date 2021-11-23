package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/18870">
 * https://www.acmicpc.net/problem/18870 좌표 압축
 * </a>
 */
public class BOJ_18870 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer token = new StringTokenizer(br.readLine(), " ");
		br.close();
		
		int[] nums = new int[N];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(token.nextToken());
		}
		
		int[] listClone = nums.clone();
		Arrays.sort(nums);
		HashMap<Integer, Integer> nMap = new HashMap<Integer, Integer>();
		
		for (int i = 0, cnt = 0; i < nums.length; i++) {
			int num = nums[i];
			if(!nMap.containsKey(num)) { // 아마 여기서 시간이 오래 걸리는듯
				nMap.put(num, cnt++);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listClone.length; i++) {
			// indexOf의 시간 복잡도 생각할 것
//			sb.append(nList.indexOf(nums[i])).append(" ");
			sb.append(nMap.get(listClone[i])).append(" ");
		}
		bw.write(sb.toString());
		bw.close();
	}
}