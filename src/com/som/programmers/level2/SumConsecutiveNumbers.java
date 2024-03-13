package com.somi.programmers;

public class SumConsecutiveNumbers {
    // [연속된 부분 수열의 합] https://school.programmers.co.kr/learn/courses/30/lessons/178870
    // '비내림차순'
    // 누적합, 투포인터

    public void test(){
        int[] sequence = null;
        int k = 0;

        // test1 - [2, 3]
        sequence = new int[]{1, 2, 3, 4, 5};
        k = 7;
        System.out.println("[" + printArray(solution(sequence, k)) + "]");

        // test2 - [6, 6]
        sequence = new int[]{1, 1, 1, 2, 3, 4, 5};
        k = 5;
        System.out.println("[" + printArray(solution(sequence, k)) + "]");

        // test3 - [0, 2]
        sequence = new int[]{2, 2, 2, 2, 2};
        k = 6;
        System.out.println("[" + printArray(solution(sequence, k)) + "]");

        // test4
        sequence = new int[]{2, 2, 2, 2, 2, 10};
        k = 10;
        System.out.println("[" + printArray(solution(sequence, k)) + "]");
    }


    // 투포인터
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, sequence.length - 1};

        int left = 0;
        int right = 0;

        int sum = sequence[right];
        while(true){
            if(sum > k) {
                // 포인터가 같으므로 right 포인터 이동 (갈 수 있으면)
                if(left == right && right != sequence.length - 1) {
                    right++;
                    sum += sequence[right];
                }
                // left 포인터 이동
                else {
                    sum -= sequence[left];
                    left++;
                }
                continue;
            }

            // right 다음 포인터 검사
            if(sum == k && (answer[1] - answer[0] > right - left)) {
                answer[0] = left;
                answer[1] = right;
            }

            right++;
            if(right == sequence.length) break;
            sum += sequence[right];
        }

        return answer;
    }


//    // 브루투포스
//    public int[] solution(int[] sequence, int k) {
//        int[] answer = new int[2];
//
//        int shortLength = Integer.MAX_VALUE;
//        for (int start = 0, sum = 0, sumLength = 0; start < sequence.length; start++, sum = 0, sumLength = 0) {
//
//            // 더하기 시작 숫자
//            for (int next = start; next < sequence.length; next++) {
//                int getNum = sequence[next];    // 이게 K보다 크거나 같은지 확인할것
//                if(getNum > k) return answer;
//
//                sum += getNum;
//                sumLength++;
//
//                if(sum >= k) {
//                    if(sum == k && shortLength > sumLength) {
//                        // 정답 업데이트
//                        shortLength = sumLength;
//                        answer[0] = start;
//                        answer[1] = next;
//                    }
//                    break;
//                }
//            }
//        }
//        return answer;
//    }

    private String printArray(int[] array) {
        return array[0] + ", " + array[1];
    }

}
