package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.TableName;

import java.util.ArrayList;
import java.util.List;

public class Student extends Academic{
    private int tblStudentId;
    private int tblStudentPersonId;
    private int tblStudentAcademicId;
    private String roleId = "S";
    public static List<Student> students = new ArrayList<>();

    public Student(String firstName, String lastName, List<String> courses) {
        super(firstName, lastName, courses);
        int lastTblStudentId = DBUtils.getLastIdFromTable(TableName.STUDENT);
        this.tblStudentId = lastTblStudentId + 1;
        this.tblStudentPersonId = getTblPersonId();
        this.tblStudentAcademicId = getTblAcademicId();
        this.roleId = roleId + 2000 +tblStudentId;
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName, new ArrayList<>());
        int lastTblStudentId = DBUtils.getLastIdFromTable(TableName.STUDENT);
        this.tblStudentId = lastTblStudentId + 1;
        this.tblStudentPersonId = getTblPersonId();
        this.tblStudentAcademicId = getTblAcademicId();
        this.roleId = roleId + 2000 +tblStudentId;
    }

    public Student() {};

    public int getTblStudentId() {
        return tblStudentId;
    }

    public void setTblStudentId(int tblStudentId) {
        this.tblStudentId = tblStudentId;
    }

    public int getTblStudentPersonId() {
        return tblStudentPersonId;
    }

    public void setTblStudentPersonId(int tblStudentPersonId) {
        this.tblStudentPersonId = tblStudentPersonId;
    }

    public int getTblStudentAcademicId() {
        return tblStudentAcademicId;
    }

    public void setTblStudentAcademicId(int tblStudentAcademicId) {
        this.tblStudentAcademicId = tblStudentAcademicId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public static void addStudent(Student student) {
        DBUtils.insertStudent(student);
        students = DBUtils.getTableStudentData();
    }

    private String getCourseName (int courseId) {
        List<Course> courses = DBUtils.getTableCourseData();
        for (Course course : courses) {
            if(course.getTblCourseId() == courseId) {
                return course.getCourseName();
            }
        }

        return "";
    }

    @Override
    public String toString() {
        return "\nStudent {\n" +
                "tblStudentId = " + getTblStudentId() + ",\n" +
                "tblPersonId = " + getTblPersonId() + ",\n" +
                "tblStudentPersonId = " + getTblStudentPersonId() + ",\n" +
                "tblAcademicId = " + getTblAcademicId() + ",\n" +
                "tblStudentAcademicId = " + getTblStudentAcademicId() + ",\n" +
                "roleId = " + getRoleId() + ",\n" +
                "firstName = '" + getFirstName() + "',\n" +
                "lastName = '" + getLastName() + "',\n" +
                "username = '" + getUserName() + "',\n" +
                "password = '" + getPassword() + "',\n" +
                "Student enrolled to courses: \n" +
                "course1 = '" + getCourseName(getCourse1()) + "',\n" +
                "course2 = '" + getCourseName(getCourse2()) + "',\n" +
                "course3 = '" + getCourseName(getCourse3()) + "',\n" +
                "course4 = '" + getCourseName(getCourse4()) + "',\n" +
                "course5 = '" + getCourseName(getCourse5()) + "',\n" +
                "course6 = '" + getCourseName(getCourse6()) + "',\n" +
                "},\n";
    }
}
