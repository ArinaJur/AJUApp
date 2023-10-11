package ajuapp;

import ajuapp.database.DBUtils;
import java.util.Scanner;

import static ajuapp.Admin.admins;

public class SignUp implements IExit {
    private void printWelcomeMessage() {
        System.out.println("        Welcome to AJU!");
        System.out.println();
    }

    private void signUp() {
        admins = DBUtils.getTableAdminData();

        if (admins.size() == 0) {
            Admin admin = new Admin("Ivan", "Sidorov");
            Admin.addAdmin(admin);
        }

        admins.get(0)
                .printAdminsList();

        printQForExit();

        Scanner in = new Scanner(System.in);

        System.out.print("Enter username: ");
        String input = in.nextLine();
        exitIfQ(input);
        String username = input;

        System.out.print("Enter password: ");
        input = in.nextLine();
        exitIfQ(input);
        String password = input;

        checkCredentials(username, password);
    }

    private void checkCredentials(String userName, String password) {
        for(Admin admin: admins) {
            if(admin.getUserName().equals(userName) && admin.getPassword().equals(password) && admin.getId().startsWith("A")) {
                System.out.println("Welcome, " + admin.getFirstName() + " " + admin.getLastName() + "!");
                admin.runAdmin();
            } else {
                exitIfAuthorizedUser();
                return;
            }
        }
    }

    public void runAJUApp() {
        printWelcomeMessage();
        signUp();

    }
}
