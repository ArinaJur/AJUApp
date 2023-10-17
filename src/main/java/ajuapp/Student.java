package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;

public final class Student extends Academic<Student> implements IPrintAcademic {
    private int tblStudentId;
    private int tblStudentPersonId;
    private int tblStudentAcademicId;
    private String roleId = "S";
    public static List<Student> students = new ArrayList<>();

    public Student() {}

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.tblStudentId = DBUtils.getLastId(Table.NAME.TBL_STUDENT) + 1;
        this.tblStudentPersonId = getTblPersonId();
        this.tblStudentAcademicId = getTblAcademicId();
        this.roleId = roleId + 2000 + tblStudentId;
    }

    public Student(int tblPersonId, String firstName, String lastName, String userName, String password,
                   int tblAcademicId, int course1, int course2, int course3, int course4, int course5, int course6,
                   int tblStudentId, int tblStudentPersonId, int tblStudentAcademicId, String roleId) {
        super(tblPersonId, firstName, lastName, userName, password, tblAcademicId, course1, course2, course3, course4, course5, course6);
        this.tblStudentId = tblStudentId;
        this.tblStudentPersonId = tblStudentPersonId;
        this.tblStudentAcademicId = tblStudentAcademicId;
        this.roleId = roleId;
    }

    public int getTblStudentId() {
        return tblStudentId;
    }

    public int getTblStudentPersonId() {
        return tblStudentPersonId;
    }

    public int getTblStudentAcademicId() {
        return tblStudentAcademicId;
    }

    public String getRoleId() {
        return roleId;
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

    @Override
    public int getAcademicId() {
        return tblStudentAcademicId;
    }

    @Override
    public char getRole() {
        return roleId.charAt(0);
    }

    @Override
    public Student getTableData(int id) {
        students = DBUtils.getTableStudentData();
        for (Student student : students) {
            if (student.getTblStudentId() == id) {
                return student;
            }
        }
        return null;
    }

    public void runStudent() {

    }
}
