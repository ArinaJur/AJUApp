package ajuapp;

import ajuapp.database.DBUtils;

import static ajuapp.Admin.admins;
import static ajuapp.Student.students;

public interface IPrintAdmin {
    String NO_ADMINS = "There are no admins registered in DB";
    String NO_STUDENTS = "There are no students registered in DB";

    default void printAdminsList() {
        admins = DBUtils.getTableAdminData();
        if (admins.size() > 0) {
            System.out.println(admins);
        } else {
            System.out.println(NO_ADMINS);
        }
    }

    default void printLastAdmin() {
        admins = DBUtils.getTableAdminData();
        if (admins.size() > 0) {
            System.out.println(admins.get(admins.size() - 1));
        } else {
            System.out.println(NO_ADMINS);
        }
    }

    default void printStudentsList() {
        students = DBUtils.getTableStudentData();
        if (students.size() > 0) {
            System.out.println(students);
        } else {
            System.out.println(NO_STUDENTS);
        }
    }

    default void printLastStudent() {
        students = DBUtils.getTableStudentData();
        if (students.size() > 0) {
            System.out.println(students.get(students.size() - 1));
        } else {
            System.out.println(NO_STUDENTS);
        }
    }

    default void printLastProfessor() {

    }

    default void printProfessorsList() {

    }
}
