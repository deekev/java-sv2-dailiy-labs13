package day01;

import java.util.*;

public class ClassNoteBook {

    private Map<Student, List<Integer>> marksByStudents = new TreeMap<>();

    public Map<Student, List<Integer>> getMarksByStudents() {
        return marksByStudents;
    }

    public void addStudent(Student student) {
        if (!marksByStudents.containsKey(student)) {
            marksByStudents.put(student, new ArrayList<>());
        } else {
            throw new IllegalArgumentException("Student already exist.");
        }
    }

    public void addMark(int id, int mark) {
        boolean found = false;
        for (Map.Entry<Student, List<Integer>> actual: marksByStudents.entrySet()) {
            if (actual.getKey().getStudentId() == id) {
                found = true;
                actual.getValue().add(mark);
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Id doesn't exist.");
        }
    }
}
