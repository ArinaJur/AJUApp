package ajuapp;

import java.util.ArrayList;
import java.util.List;

public class Student extends Academic{
    private String id = "S";
    private static int studentID = 1000001;
    private static List<Student> students = new ArrayList<>();

    public Student(String firstName, String lastName, List<String> courses) {
        super(firstName, lastName, courses);
        this.id = id + studentID;
        studentID ++;
    }

    public void addStudent(Student student) {
        students.add(student);
    }


}
