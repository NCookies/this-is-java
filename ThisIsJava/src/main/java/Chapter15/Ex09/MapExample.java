package Chapter15.Ex09;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("blue", 96);
        map.put("hong", 86);
        map.put("white", 92);

        String name = null;
        int maxScore = 0;
        int totalScore = 0;

        Set<String> keySet = map.keySet();
        Iterator<String> keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            int value = map.get(key);
            totalScore += value;
            if (value > maxScore) {
                name = key;
                maxScore = value;
            }
        }

        System.out.println("평균점수 : " + (totalScore / 3));
        System.out.println("최고점수 : " + maxScore);
        System.out.println("최고점수를 받은 아이디 : " + name);
    }
}
