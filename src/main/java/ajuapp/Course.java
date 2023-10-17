package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;

public final class Course {
    final private int tblCourseId;
    final private String courseName;
    final private int price;
    public static List<Course> courses = new ArrayList<>();

    public Course(String courseName, int price) {
        final int lastTblCourseId = DBUtils.getLastId(Table.NAME.TBL_COURSE);
        this.tblCourseId = lastTblCourseId + 1;
        this.courseName = courseName;
        this.price = price;
    }

    public Course(int tblCourseId, String courseName, int price) {
        this.tblCourseId = tblCourseId;
        this.courseName = courseName;
        this.price = price;
    }

    public int getTblCourseId() {
        return tblCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "\ncourseId = " + getTblCourseId() + ",\t" +
                "courseName = '" + getCourseName() + "',\t" +
                "price = '" + getPrice() + "'";
    }
}
