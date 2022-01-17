package day01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClassNoteBookTest {

    ClassNoteBook classNoteBook = new ClassNoteBook();

    @BeforeEach
    void init() {
        Student student1 = new Student(3, "Kis Balázs");
        Student student2 = new Student(1, "Nagy Dóra");
        Student student3 = new Student(2, "Tóth Gábor");

        classNoteBook.addStudent(student1);
        classNoteBook.addStudent(student2);
        classNoteBook.addStudent(student3);
    }

    @Test
    void testAddStudent() {
        Student student4 = new Student(4, "Juhász Éva");
        classNoteBook.addStudent(student4);

        assertEquals(4, classNoteBook.getMarksByStudents().size());
        assertTrue(classNoteBook.getMarksByStudents().containsKey(student4));
    }

    @Test
    void testAddStudentWithExistingId() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> classNoteBook.addStudent(new Student(1, "Kis Balázs")));
        assertEquals("Student already exist.", exception.getMessage());
    }

    @Test
    void testAddStudentInOrder() {
        Student student4 = new Student(4, "Juhász Éva");
        classNoteBook.addStudent(student4);

        Set<Student> actual = classNoteBook.getMarksByStudents().keySet();
        List<Student> expected = List.of(
                new Student(1, "Nagy Dóra"),
                new Student(2, "Tóth Gábor"),
                new Student(3, "Kis Balázs"),
                new Student(4, "Juhász Éva"));

        assertEquals(expected, new ArrayList<>(actual));
    }

    @Test
    void testAddMark() {
        Student student4 = new Student(4, "Juhász Éva");
        classNoteBook.addStudent(student4);

        classNoteBook.addMark(4, 4);
        classNoteBook.addMark(4, 2);
        classNoteBook.addMark(4, 3);

        assertEquals(List.of(4, 2, 3), classNoteBook.getMarksByStudents().get(student4));
    }

    @Test
    void testAddMarkWithInvalidId() {

        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> classNoteBook.addMark(5, 5));
        assertEquals("Id doesn't exist.", exception.getMessage());
    }
}