package ajuapp;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Person {
    private String id = "A";
    private static int adminID = 1000001;
    private static List<Admin> admins = new ArrayList<>();

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id + adminID;
        adminID ++;
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static void printAdmins() {
        for(Admin admin : admins) {
            System.out.println(admin.getFirstName());
            System.out.println(admin.getLastName());
            System.out.println(admin.getPassword());
            System.out.println(admin.getUserName());
            System.out.println(admin.id);
        }
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static void runAdmin(){

    }

}
