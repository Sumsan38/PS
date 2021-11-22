package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @see <a href="https://www.acmicpc.net/problem/3272">
 * https://www.acmicpc.net/problem/3272 두 수의 합
 * </a>
 */
public class BOJ_3273 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer token = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(token.nextToken());
		}
		int x = Integer.parseInt(br.readLine());
		br.close();
		
		Arrays.sort(nums);
		int cnt = 0;
		int startP = 0;
		int endP = n-1;
		while(true) {
			int left = nums[startP];
			int right = nums[endP];
			
			if(left == right) break;
			if(left + right <= x) {
				if(left + right == x) cnt++;
				startP++;
			} else {
				endP--;
			}
		}
		System.out.println(cnt);
	}
	
}
