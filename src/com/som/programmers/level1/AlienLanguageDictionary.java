package com.som.programmers.easy;

public class AlienLanguageDictionary {
    // [외계어 사전] https://school.programmers.co.kr/learn/courses/30/lessons/120869

    public void test(){
        String[] spell;
        String[] dic;

        // 2
        spell = new String[]{"p", "o", "s"};
        dic = new String[]{"sod", "eocd", "qixm", "adio", "soo"};
        System.out.println(solution(spell, dic));

        // 1
        spell = new String[]{"z", "d", "x"};
        dic = new String[]{"def", "dww", "dzx", "loveaw"};
        System.out.println(solution(spell, dic));

        // 2
        spell = new String[]{"s", "o", "m", "d"};
        dic = new String[]{"moos", "dzx", "smm", "sunmmo", "som"};
        System.out.println(solution(spell, dic));
    }


    public int solution(String[] spell, String[] dic) {
        for (String word : dic) {
            if(word.length() == spell.length) {
                boolean allContains = true;
                for (String alphabet : spell) {
                    if (! word.contains(alphabet)) {
                        allContains = false;
                        break;
                    }
                }
                if(allContains) return 1;
            }
        }

        return 2;
    }
}
