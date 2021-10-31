package com.somi;

public class MyCircularQueue {

	private Integer[] queue;
	int head = -1; // deQueue에 관련된 것
	int tail = -1; // enQueue에 관련된 것

	int queueSize = 0;

	public MyCircularQueue(int k) {
		// Initializes the object with the size of the queue to be k.
		queueSize = k;
		queue = new Integer[k];
	}

	public int Front() {
		// Gets the front item from the queue. If the queue is empty, return -1.
		if(isEmpty()){
			return -1;
		} else 
			return queue[head];
	}

	public int Rear() {
		// Gets the last item from the queue. If the queue is empty, return -1.
		if(isEmpty()){
			return -1;
		} else 
			return queue[tail];
	}

	public boolean enQueue(int value) {
		// Inserts an element into the circular queue. 
		// Return true if the operation is successful.
		if(isFull()) {
			return false;
		} else {
			if(isEmpty()){ // 최초 삽입 head와 tail을 옮긴다
				head++;
				tail++;
				queue[head] = value;
			} else if(tail == queueSize - 1) {
				// 가득 찬것도, 빈 상태도 아닐 때
				// tail이 마지막 블록을 가리키고 있다면 처음으로 이동 
				tail = 0;
				queue[tail] = value;
			} else{
				// 그냥 옆 칸으로 이동 가능
				tail++;
				queue[tail] = value;
			}
			return true;
		}
	}

	public boolean deQueue() {
		// Deletes an element from the circular queue. 
		// Return true if the operation is successful.
		if(isEmpty()) {
			return false;
		} else {
			// queue[head] = -1; // 값 채워 넣기
			if(head == tail) { // 한칸만 채워져 있는 경우, 뺀 뒤에 isEmpty 상태로 해야함
				head = -1;
				tail = -1;
			} else if(head == queueSize - 1){
				// isEmpty도 아니고, 끝이에서 값을 뺀 뒤 맨 앞칸으로 옮기기
				head = 0;
			} else {
				// 다음 칸으로 진행
				head++;
			}
			return true;
		}
	}

	public boolean isEmpty() {
		// Checks whether the circular queue is empty or not.
		if(head == -1 && tail == -1) {
			return true;
		} else 
			return false;
	}

	public boolean isFull() {
		// Checks whether the circular queue is full or not.
		// 조건 1 head가 맨 앞이고 tail이 맨 뒤
		if((head == 0 && head + tail == queue.length -1)
			// 조건 2 head가 tail보다 뒤에 있고 tail이 head 바로 앞
			|| (head > tail && head - 1 == tail)) {
			return true;
		} else
			return false;
	}
}