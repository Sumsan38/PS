package com.somi.leetcode.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String commandString = br.readLine();
		commandString = commandString.substring(2, commandString.length() - 2);
		StringTokenizer token = new StringTokenizer(commandString, "\", \"");
		int commandNum = token.countTokens();
		String[] commands = new String[commandNum];
		for (int i = 0; i < commandNum; i++) {
			String command = token.nextToken();
			commands[i] = command;
		}
		
		String inputInt = br.readLine();
		inputInt = inputInt.substring(1, inputInt.length() - 1);
		// [3], [1], [2], [3], [4], [], [], [], [4], []
		StringTokenizer intToken = new StringTokenizer(inputInt, ", ");
		int inputNum = intToken.countTokens();
		int[] inputs = new int[inputNum];
		for (int i = 0; i < inputNum; i++) {
			String inputStr = intToken.nextToken();
			if(inputStr.length() > 2){
				inputs[i] = Integer.parseInt(inputStr.substring(1, inputStr.length() - 1));
			} else continue;
		}
		br.close();
		
		
		StringBuilder sb = new StringBuilder();
		
		MyCircularQueue myCircularQueue = null;
		sb.append("[");
		for (int i = 0; i < commands.length; i++) {
			switch(commands[i]) {
			case "MyCircularQueue":
				myCircularQueue = new MyCircularQueue(inputs[i]);
				sb.append("null");
				break;
			case "Front":
				sb.append(myCircularQueue.Front());
				break;
			case "Rear":
				sb.append(myCircularQueue.Rear());
				break;
			case "enQueue":
				sb.append(myCircularQueue.enQueue(inputs[i]));
				break;
			case "deQueue":
				sb.append(myCircularQueue.deQueue());
				break;
			case "isEmpty":
				sb.append(myCircularQueue.isEmpty());
				break;
			case "isFull":
				sb.append(myCircularQueue.isFull());
				break;
			}
			if(i != commands.length - 1) sb.append(",");
		}
		sb.append("]");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}