package com.baekjoon;

import java.io.*;

/**
 *@see<a href="https://www.acmicpc.net/problem/11279">
 * https://www.acmicpc.net/problem/11279 최대 힙
 *</a>
 */
public class BOJ_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Heap heap = new Heap(100000);
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if(command == 0) {
                sb.append(heap.removeHeap()).append("\n");
            } else {
                heap.insertHeap(command);
            }
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static class Heap {
        int[] heap;
        int heapPoint = 0;
        public Heap(int N) {
            heap = new int[N];
        }
        public void insertHeap(int input){
            heap[heapPoint] = input; // 노드 삽입
            if(heapPoint != 0) {
                int checkHeapPoint = heapPoint;
                int parentPoint = 0;
                while (checkHeapPoint != 0) {
                    // 부모 값과 비교하면서 삽입 // 부모와 같은 값일 경우 그냥 삽입
                    if(checkHeapPoint % 2 == 0) {
                        parentPoint = (checkHeapPoint - 2) / 2; // 부모 값 오른쪽 노드
                    } else {
                        parentPoint = (checkHeapPoint - 1) / 2; // 부모 값 왼쪽 노드
                    }
                    if(heap[checkHeapPoint] > heap[parentPoint]) {
                        int temp = heap[parentPoint];
                        heap[parentPoint] = heap[checkHeapPoint];
                        heap[checkHeapPoint] = temp;
                        checkHeapPoint = parentPoint;
                    } else break;
                }
            }
            heapPoint++;
        }

        public int removeHeap() { // heapPoint 비어져 있는 곳 시작점
            int root = heap[0];
            if(root == 0) return 0;

            int leef = heap[(heapPoint-- - 1)];
            if(heapPoint == 0) {
                heap[0] = 0;
                return root;
            }

            heap[heapPoint] = 0;
            heap[0] = leef;
            // 끝자리꺼 root로 올리고 자식 노드들과 비교 // 자식들보다 작다면 탐색 종료
            int currentPoint = 0;
            while(true) {
                int swapPoint = 0;
                int swapValue = 0;

                // 자식 노드가 heapPoint를 넘어가면 break 해야겠는데 // 왼쪽 노드 있으면 값 하나 비교
                int leftPoint = currentPoint * 2 + 1; // 부모 값 오른쪽 노드
                if(leftPoint >= heapPoint) break;
                int leftValue = heap[leftPoint];
                if(leftPoint != (heapPoint - 1)) { // 오른쪽도 있는지 확인
                    int rightPoint = currentPoint * 2 + 2; // 부모 값 왼쪽 노드
                    if (rightPoint <= heapPoint) {
                        int rightValue = heap[rightPoint];
                        // 둘 중에 큰 값이랑 자리를 바꿈
                        if (leef < rightValue || leef < leftValue) {
                            if (rightValue > leftValue) {
                                swapPoint = rightPoint;
                                swapValue = rightValue;
                            } else {
                                swapPoint = leftPoint;
                                swapValue = leftValue;
                            }
                        }
                    }
                } else {
                    // 왼쪽과 비교
                    if (leftValue > leef) {
                        swapPoint = leftPoint;
                        swapValue = leftValue;
                    }
                }
                if (swapValue == 0) break;
                // currentPoint와 변경
                int temp = heap[currentPoint];
                heap[currentPoint] = swapValue;
                heap[swapPoint] = temp;
                currentPoint = swapPoint;
            }
            return root;
        }
    }

}