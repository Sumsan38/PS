package com.som.main;

import com.som.problem.programmers.AthleteCantFinish;

public class TestMain {

	public static void main(String[] args) {
		// Sysout Print Value
		
		AthleteCantFinish test = new AthleteCantFinish();
		
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};

//		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		
//		String[] participant = {"mislav", "stanko", "mislav", "ana"};
//		String[] completion = {"stanko", "ana", "mislav"};
		
		String nameOne = test.solution(participant, completion);
		System.out.println("Check One :: " + nameOne);
	}
	
	
}
