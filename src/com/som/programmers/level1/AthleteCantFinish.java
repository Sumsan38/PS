package com.som.programmers.easy;

import java.util.HashMap;
import java.util.Map;

public class AthleteCantFinish {
	// https://programmers.co.kr/learn/courses/30/lessons/42576?language=java
	
	public AthleteCantFinish(){
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};

//		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		
//		String[] participant = {"mislav", "stanko", "mislav", "ana"};
//		String[] completion = {"stanko", "ana", "mislav"};
		
		String nameOne = solution(participant, completion);
		System.out.println("Solution :: " + nameOne);
	}
	
	public String solution(String[] participant, String[] completion) {
		String answer = "";
		Map<String, Integer> participantMap = new HashMap<>();
		for (String name : participant) {
				participantMap.put(name, participantMap.getOrDefault(name, 0)+1);
		}
		
		for (String name : completion) {
			participantMap.put(name, participantMap.get(name)-1);
		}
		
		for (String name : participantMap.keySet()) {
			if(participantMap.get(name) == 1){
				answer = name;
				break;
			}
		}
		return answer;
    }
    
    

}
