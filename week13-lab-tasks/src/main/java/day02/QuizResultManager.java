package day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class QuizResultManager {

    private List<String> rightAnswers = new ArrayList<>();
    private Map<String, List<String>> answersByPerson = new HashMap<>();

    public List<String> getRightAnswers() {
        return rightAnswers;
    }

    public Map<String, List<String>> getAnswersByPerson() {
        return answersByPerson;
    }

    public void readData(String filename) {
        try(BufferedReader bf = Files.newBufferedReader(Path.of(filename))) {
            fillRightAnswers(bf.readLine());
            String line;
            while ((line = bf.readLine()) != null) {
                createEntries(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't reach file.");
        }
    }

    public Answer isAnswerRight(String id, int numberOfQuestion) {
        String rightAnswer = rightAnswers.get(numberOfQuestion - 1);
        String answerByPerson = answersByPerson.get(id).get(numberOfQuestion - 1);
        if (answerByPerson.equals(rightAnswer)) {
            return Answer.RIGHT_ANSWER;
        } else if ("X".equals(answerByPerson)) {
            return Answer.PASS;
        } else {
            return Answer.WRONG_ANSWER;
        }
    }

    public String getWinnerId() {
        String id = "";
        int maxPoints = 0;
        for (Map.Entry<String, List<String>> actual: answersByPerson.entrySet()) {
            int points = countPoints(actual);
            if (!answersByPerson.containsKey(id)) {
                id = actual.getKey();
                maxPoints = points;
            }
            if (points > maxPoints) {
                maxPoints = points;
                id = actual.getKey();
            }
        }
        return id;
    }

    private void fillRightAnswers(String line) {
        for (char c: line.toCharArray()) {
            rightAnswers.add(String.valueOf(c));
        }
    }

    private void createEntries(String line) {
        String[] temp = line.split(" ");
        String key = temp[0];
        String valueElement = temp[1];
        if (!answersByPerson.containsKey(key)) {
            answersByPerson.put(key, new ArrayList<>());
        }
        answersByPerson.get(key).add(valueElement);
    }

    private int countPoints(Map.Entry<String, List<String>> entry) {
        int points = 0;
        for (int i = 1; i <= entry.getValue().size(); i++) {
            if (isAnswerRight(entry.getKey(), i) == Answer.RIGHT_ANSWER) {
                points += isAnswerRight(entry.getKey(), i).getPoints() * i;
            } else {
                points += isAnswerRight(entry.getKey(), i).getPoints();
            }
        }
        return points;
    }
}