package ajuapp;

import ajuapp.database.DBUtils;

import static ajuapp.Course.courses;

public interface IPrintAcademic {
    String NO_COURSES = "There are no courses available for registration";

    default void printCoursesList() {
        courses = DBUtils.getTableCourseData();
        if (courses.size() > 0) {
            System.out.println("Available Courses:");
            System.out.println(courses);
            System.out.println();
        } else {
            System.out.println(NO_COURSES);
        }
    }
}
