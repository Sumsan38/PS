package com.som.programmers.easy;

import java.util.HashMap;
import java.util.Map;

public class MostReceivedGift {
    // 2024 KAKAO WINTER INTERNSHIP
    // https://school.programmers.co.kr/learn/courses/30/lessons/258712?language=java
    // 가장 많이 받은 선물 [문제 해석]
    // String, HashMap (key, value)

    public int solution(String[] friends, String[] gifts) {
        // 친구가 선물을 준 횟수 array
        Map<String, Map<String, Integer>> giftsMap = new HashMap<>();       // 자기 자신에게는 선물 지수
        for (String friend : friends) {
            giftsMap.put(friend, new HashMap<>());
            giftsMap.get(friend).put(friend, 0);
        }

        // map into map 전환
        for (String gift : gifts) {
            String[] fromTo = gift.split(" ");
            String from = fromTo[0];        // 준 사람
            String to = fromTo[1];          // 받은 사람

            // 받은 사람 선물 지수 업데이트
            Map<String, Integer> toGiftMap = giftsMap.get(to);
            toGiftMap.put(to, toGiftMap.get(to) - 1);

            // 자기 자신의 선물 지수 업데이트
            Map<String, Integer> fromToMap = giftsMap.get(from);
            fromToMap.put(from, fromToMap.get(from) + 1);

            // 친구에게 준 선물 업데이트
            if(! fromToMap.containsKey(to)) {
                fromToMap.put(to, 1);
            } else {
                fromToMap.put(to, fromToMap.get(to) + 1);
            }
        }

        int mostGiftCnt = 0;
        for (String fromName : friends) {
            int getGiftCnt = 0;     // fromName 이 받는 선물 개수
            Map<String, Integer> fromFriendMap = giftsMap.get(fromName);  // fromName 준 이름과 횟수
            Integer fromGiftCnt = fromFriendMap.get(fromName);            // fromName의 선물 지수

            for (String toName : friends) {
                if(fromName.equalsIgnoreCase(toName)) continue;
                Map<String, Integer> reverseFriendMap = giftsMap.get(toName);
                Integer toGiftCnt = reverseFriendMap.get(toName);           // toName의 선물 지수

                Integer sendCnt = fromFriendMap.getOrDefault(toName, 0);                // fromName이 toName에게 준 횟수
                Integer reverseSendCnt = reverseFriendMap.getOrDefault(fromName, 0);    // toName이 fromNAme에게 준 횟수

                if((sendCnt == 0 && reverseSendCnt == 0) || sendCnt.equals(reverseSendCnt)) {
                    // 1. 서로 주고 받은 적 없음, 서로 주고 받은 수가 같음 = 선물 지수를 비교해서 받기
                    if(fromGiftCnt > toGiftCnt) {
                        getGiftCnt++;
                    }
                } else if(sendCnt > reverseSendCnt) {
                    // 2. 하나라도 서로 주고 받은 흔적이 있음 = 받은 횟수 검사
                    getGiftCnt++;
                }
            }

            mostGiftCnt = Math.max(mostGiftCnt, getGiftCnt);
        }

        return mostGiftCnt;
    }
}
