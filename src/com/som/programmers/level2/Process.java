package com.som.programmers.level2;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Process {
    // [프로세스] https://school.programmers.co.kr/learn/courses/30/lessons/42587

    public void test() {
        int[] priorities;
        int location;

        //1
        priorities = new int[]{2, 1, 3, 2};
        location = 2;
        System.out.println(solution(priorities, location));

        //5
        priorities = new int[]{1, 1, 9, 1, 1, 1};
        location = 0;
        System.out.println(solution(priorities, location));
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int priority : priorities) {
            queue.offer(priority);
        }

        while(! queue.isEmpty()) {
            // 0 ~ priorities.length - 1 까지 순회하면서 우선순위 높은 작업을 먼저 꺼냄
            // [1, 1, 9, '1, 1, 1'] 예제도 9번부터 먼저 작업한 뒤, 그 뒤의 '1, 1, 1' 부분이 먼저 처리되는 방식으로 첫번째 loop가 끝난다
            // 다시 0 ~ priorities.length - 1 까지 재 순회 하면서 0번쨰 자리의 1을 처리하면서 return
            for (int i = 0; i < priorities.length; i++) {
                Integer nextHighPriority = queue.peek();
                if(nextHighPriority == priorities[i]) {
                    queue.poll();
                    answer++;

                    if(location == i) {
                        return answer;
                    }
                }
            }
        }

        return answer;
    }
}
