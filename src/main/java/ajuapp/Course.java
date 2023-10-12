package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.TableName;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int tblCourseId;
    private String courseName;
    private int price;
    public static List<Course> courses = new ArrayList<>();

    public Course() {};

    public Course(String courseName, int price) {
        int lastTblCourseId = DBUtils.getLastIdFromTable(TableName.COURSE);
        this.tblCourseId = lastTblCourseId + 1;
        this.courseName = courseName;
        this.price = price;
    }

    public int getTblCourseId() {
        return tblCourseId;
    }

    public void setTblCourseId(int tblCourseId) {
        this.tblCourseId = tblCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void addCourse(Course course) {
        DBUtils.insertCourse(course);
        courses = DBUtils.getTableCourseData();
    }

    @Override
    public String toString() {
        return "courseId = " + getTblCourseId() + ",\t" +
                "courseName = '" + getCourseName() + "',\t" +
                "price = '" + getPrice() + "'\n";
    }
}
