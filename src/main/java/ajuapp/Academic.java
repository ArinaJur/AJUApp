package ajuapp;

import java.util.ArrayList;
import java.util.List;

public class Academic extends Person{
    private int tblAcademicId;
    private int course1;
    private int course2;
    private int course3;
    private int course4;
    private int course5;
    private int course6;
    private static int idAcademic = 1;
    public static List<Academic> courses = new ArrayList<>();


    public Academic(String firstName, String lastName,  List<String> courses) {
        super(firstName, lastName);
        this.tblAcademicId = idAcademic;
        idAcademic ++;
    }

    public Academic() {};

    public static void getCourses(List<String> courses) {
        courses.addAll(courses);
    }

    public int getTblAcademicId() {
        return tblAcademicId;
    }

    public void setTblAcademicId(int tblAcademicId) {
        this.tblAcademicId = tblAcademicId;
    }

    public int getCourse1() {
        return course1;
    }

    public void setCourse1(int course1) {
        this.course1 = course1;
    }

    public int getCourse2() {
        return course2;
    }

    public void setCourse2(int course2) {
        this.course2 = course2;
    }

    public int getCourse3() {
        return course3;
    }

    public void setCourse3(int course3) {
        this.course3 = course3;
    }

    public int getCourse4() {
        return course4;
    }

    public void setCourse4(int course4) {
        this.course4 = course4;
    }

    public int getCourse5() {
        return course5;
    }

    public void setCourse5(int course5) {
        this.course5 = course5;
    }

    public int getCourse6() {
        return course6;
    }

    public void setCourse6(int course6) {
        this.course6 = course6;
    }
}
