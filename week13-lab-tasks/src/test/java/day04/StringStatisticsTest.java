package day04;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class StringStatisticsTest {

    StringStatistics stringStatistics = new StringStatistics();

    @Test
    void testCountVowels() {

        stringStatistics.countVowels("This Is a probe");

        assertEquals(4, stringStatistics.getVowelsWithNumber().size());
        assertEquals(2, stringStatistics.getVowelsWithNumber().get("i"));

        Map<String, Integer> expected = new TreeMap<>();
        expected.put("i", 2);
        expected.put("a", 1);
        expected.put("o", 1);
        expected.put("e", 1);

        assertEquals(expected, stringStatistics.getVowelsWithNumber());
    }
}