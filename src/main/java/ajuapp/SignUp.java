package ajuapp;

import ajuapp.database.DBUtils;
import java.util.Scanner;

import static ajuapp.Admin.admins;
import static ajuapp.Professor.professors;
import static ajuapp.Student.students;

public final class SignUp implements IExit {

    private void printWelcomeMessage() {
        System.out.println("        Welcome to AJU!");
        System.out.println();
    }

    private void signUp() {
        admins = DBUtils.getTableAdminData();
        students = DBUtils.getTableStudentData();
        professors = DBUtils.getTableProfessorData();

        if (admins.size() == 0) {
            Admin.addAdmin(new Admin("Ivan", "Sidorov"));
        }

        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.print("Enter username: ");
        String input = in.nextLine();
        exitIfQ(input);
        final String username = input;

        System.out.print("Enter password: ");
        input = in.nextLine();
        exitIfQ(input);
        final String password = input;

        checkCredentials(username, password);
    }

    private void checkCredentials(String userName, String password) {
        for(Admin admin: admins) {
            if(admin.getUserName().equals(userName) && admin.getPassword().equals(password) && admin.getRole() == 'A') {
                System.out.println("\n\n\nWelcome, " + admin.getFirstName() + " " + admin.getLastName() + "!\n");
                admin.runAdmin();
            }
        }
        for (Student student : students) {
            if(student.getUserName().equals(userName) && student.getPassword().equals(password) && student.getRole() == 'S') {
                System.out.println("Welcome, " + student.getFirstName() + " " + student.getLastName() + "!");
                student.runStudent();
            }
        }
        for (Professor professor : professors) {
            if(professor.getUserName().equals(userName) && professor.getPassword().equals(password) && professor.getRole() == 'P') {
                System.out.println("Welcome, " + professor.getFirstName() + " " + professor.getLastName() + "!");
                professor.runProfessor();
            }
        }

        exitIfUnauthorizedUser();
    }

    public void runAJUApp() {
        printWelcomeMessage();
        signUp();
    }
}
