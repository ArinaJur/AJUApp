package ajuapp.database;

import ajuapp.Admin;
import ajuapp.Student;

import java.util.Arrays;
import java.util.List;

public final class DBClear {
    public static void main(String[] args) {
        DBUtils.dropTable(Table.NAME.TBL_PERSON);
        DBUtils.dropTable(Table.NAME.TBL_ADMIN);
        DBUtils.dropTable(Table.NAME.TBL_STUDENT);
        DBUtils.dropTable(Table.NAME.TBL_PROFESSOR);
        DBUtils.dropTable(Table.NAME.TBL_ACADEMIC);
//        DBUtils.dropTable(Table.NAME.TBL_COURSE);

        final List<Admin> dbUsers = DBUtils.getTablePersonData();
        final List<Admin> dbAdmins = DBUtils.getTableAdminData();
        final List<Student> dbStudents = DBUtils.getTableStudentData();

        System.out.println("Person:");
        System.out.println(Arrays.toString(dbUsers.toArray()));
        System.out.println("Admin:");
        System.out.println(Arrays.toString(dbAdmins.toArray()));
        System.out.println("Student:");
        System.out.println(Arrays.toString(dbStudents.toArray()));
    }
}
