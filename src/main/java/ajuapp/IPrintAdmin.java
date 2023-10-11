package ajuapp;

import static ajuapp.Admin.admins;
import static ajuapp.Student.students;

public interface IPrintAdmin {

    default void printAdminsList() {
        System.out.println(admins);
    }

    default void printStudentsList() {
        System.out.println(students);
    }
}
