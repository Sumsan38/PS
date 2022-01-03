package baekjoon;

import java.io.*;
import java.util.StringTokenizer;
/**
 * @see <a href="https://www.acmicpc.net/problem/13305">
 * https://www.acmicpc.net/problem/13305 ¡÷¿Øº“
 * </a>
 */
public class BOJ_13305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer roadToken = new StringTokenizer(br.readLine(), " ");
		StringTokenizer cityToken = new StringTokenizer(br.readLine(), " ");
		br.close();
		
		long[] road = new long[roadToken.countTokens()];
		for (int i = 0; i < road.length; i++) {
			road[i] = Long.parseLong(roadToken.nextToken());
		}
		long[] city = new long[cityToken.countTokens()];
		for (int i = 0; i < city.length; i++) {
			city[i] = Long.parseLong(cityToken.nextToken());
		}
		
		
		long minCost =city[0];
		long money = minCost * road[0];
		
		for (int i = 1; i < N - 1; i++) {
			long cityCost = city[i];
			minCost = minCost > cityCost? cityCost : minCost;
			money += minCost * road[i];
		}
		
		System.out.println(money);
		
	}
}
