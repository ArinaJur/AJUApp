package ajuapp.database;

import ajuapp.Admin;
import ajuapp.Person;

import java.util.Arrays;
import java.util.List;

public class DBClear {
    public static void main(String[] args) {
        DBUtils.dropTableAdmin();
        DBUtils.dropTablePerson();

        List<Person> dbUsers = DBUtils.getTablePersonData();
        List<Admin> dbAdmins = DBUtils.getTableAdminData();

        System.out.println("Persons:");
        System.out.println(Arrays.toString(dbUsers.toArray()));
        System.out.println("Admin:");
        System.out.println(Arrays.toString(dbAdmins.toArray()));
    }
}
