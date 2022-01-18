package day02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuizResultManagerTest {

    QuizResultManager quizResultManager = new QuizResultManager();

    @BeforeEach
    void init() {
        quizResultManager.readData("src/main/resources/result.txt");
    }

    @Test
    void testInit() {

        assertEquals(4, quizResultManager.getAnswersByPerson().size());
        assertEquals(Arrays.asList("A", "B", "A", "C", "D"), quizResultManager.getRightAnswers());
        assertEquals(Arrays.asList("A", "C", "C", "B", "X"), quizResultManager.getAnswersByPerson().get("AB123"));
    }

    @Test
    void testIsAnswerRight() {

        assertEquals(Answer.RIGHT_ANSWER, quizResultManager.isAnswerRight("AB123", 1));
        assertEquals(Answer.WRONG_ANSWER, quizResultManager.isAnswerRight("AB123", 2));
        assertEquals(Answer.PASS, quizResultManager.isAnswerRight("AB123", 5));
    }

    @Test
    void testGetWinnerId() {

        String winnerId = quizResultManager.getWinnerId();

        assertEquals("GH1234", winnerId);
    }
}