package day04;

import java.util.Map;
import java.util.TreeMap;

public class StringStatistics {

    private Map<String, Integer> vowelsWithNumber = new TreeMap<>();
    public static final String VOWELS = "aeiou";

    public Map<String, Integer> getVowelsWithNumber() {
        return vowelsWithNumber;
    }

    public void countVowels(String text) {
        for (int i = 0; i < text.length(); i++) {
            String character = String.valueOf(text.charAt(i)).toLowerCase();
            if (VOWELS.contains(character)) {
                createMapEntry(character);
            }
        }
    }

    private void createMapEntry(String character) {
        if (! vowelsWithNumber.containsKey(character)) {
            vowelsWithNumber.put(character, 1);
        } else {
            vowelsWithNumber.put(character, vowelsWithNumber.get(character) + 1);
        }
    }
}
