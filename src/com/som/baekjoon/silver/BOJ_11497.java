package com.somi.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11497 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			int[] logs = new int[N];
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				logs[j] = Integer.parseInt(token.nextToken());
			}
			
			sb.append(sortLogsReturnAnswer(logs)).append("\n");
		}
		br.close();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int sortLogsReturnAnswer(int[] logs){
		//	배치하면서 난이도 체크
		int diff = 0;
		Arrays.sort(logs);

		// 두 포인터 방식으로 끝에서부터 배치
		int startP = 0;
		int endP = logs.length - 1;
		int index = 0;
		int[] fillLogs = new int[logs.length];
		int[] currentDiff = {0, 0};
		while(true) {
			try {
				fillLogs[startP] = logs[index++];
				fillLogs[endP] = logs[index++];
			} catch(ArrayIndexOutOfBoundsException e){
				break;
			} finally {
				// 양 옆과의 높이 비교
				if(startP == 0) {
					currentDiff[0] = Math.abs(fillLogs[startP] - fillLogs[endP]);
				} else {
					// startDiff의 경우 왼쪽과 비교
					currentDiff[0] = Math.abs(fillLogs[startP] - fillLogs[startP - 1]);
					// endDiff의 경우 오른쪽과 비교
					currentDiff[1] = Math.abs(fillLogs[endP] - fillLogs[endP + 1]);
				}
				diff = diff >= currentDiff[0] ? diff : currentDiff[0];
				diff = diff >= currentDiff[1] ? diff : currentDiff[1];
				
				currentDiff[0] = 0;
				currentDiff[1] = 0;

				startP++;
				endP--;
			}
		}
		
		return diff;
	}
}
