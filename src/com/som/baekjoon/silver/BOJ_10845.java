package com.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/* https://www.acmicpc.net/problem/10845 큐 */
public class BOJ_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        MyQueue queue = new MyQueue(n);

        StringTokenizer command;
        for (int i = 0; i < n; i++) {
            command = new StringTokenizer(br.readLine(), " ");
            switch (command.nextToken()) {
                case "push":
                    queue.push(Integer.parseInt(command.nextToken()));
                    break;
                case "pop":
                    sb.append(queue.pop()).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    sb.append(queue.empty()).append("\n");
                    break;
                case "front":
                    sb.append(queue.front()).append("\n");
                    break;
                case "back":
                    sb.append(queue.back()).append("\n");
                    break;
            }
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

 class MyQueue {
    Node head,tail;
    int size =0,currSize =0;
    class Node {
        Node next,prev;
        int val;
        Node(int val) {
            this.next = next;
            this.prev = prev;
            this.val = val;
        }
    }

    public MyQueue(int k) {
        this.size = k;
    }

    public boolean push(int value) {
        if(this.currSize < this.size) {
            if(head == null)
                tail = head = new Node(value);
            else {
                Node addition = new Node(value);
                tail.next = addition;
                addition.prev = tail;
                tail = tail.next;
            }
            this.currSize++;
            return true;
        }
        return false;
    }

    public int pop() {
        if(this.currSize > 0) {
            int returValue = head.val;
            if(head != null) head = head.next;
            if(head != null) head.prev = null;
            if(head == null) tail = null;
            this.currSize--;
            return returValue;
        }
        return -1;
    }

    public int front() {
        return head != null ? head.val : -1;
    }

    public int back() {
        return tail != null ? tail.val : -1;
    }

    public int empty() {
        return head == null ? 1 : 0;
    }

    public int size() {
        return currSize;
    }
}