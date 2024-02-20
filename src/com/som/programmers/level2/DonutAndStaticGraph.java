package com.somi.programmers;

public class DonutAndStaticGraph {
    // 2024 KAKAO WINTER INTERNSHIP
    // [도넛과 막대 그래프] https://school.programmers.co.kr/learn/courses/30/lessons/258711
    // 문제 해석, 구현

    public int[] solution(int[][] edges) {
        // 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프
        int[] answer = new int[4];
        int MAX = 1000001;

        int[] in = new int[MAX];
        int[] out = new int[MAX];

        for (int[] edge : edges) {
            int start = edge[0];
            out[start]++;
            int end = edge[1];
            in[end]++;
        }

        // 정점 찾기
        for (int i = 1; i < MAX; i++) {
            if(in[i] == 0 && out[i] > 1) {
                answer[0] = i;
                break;
            }
        }

        // 모든 그래프 수 out[answer[0]]
        // answer[2] : in[point] >= 1, out[point] = 0 들어오는 선은 있고 나가는 선은 없음 막대 모양 확정
        // answer[3] : out[point] >= 2 나가는 선 2개 8자 모양 확정
        // answer[1] 그 외엔 도넛 모양이다
        for (int point = 1; point < MAX; point++) {
            if(point == answer[0]) continue;

            if(out[point] >= 2) answer[3]++;
            else if(in[point] >= 1 && out[point] == 0) answer[2]++;
        }

        answer[1] = out[answer[0]] - answer[2] - answer[3];
        return answer;
    }
}
