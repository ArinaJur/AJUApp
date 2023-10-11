package ajuapp;

import ajuapp.database.DBUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person implements IPrintAdmin, IExit {
    private String id = "A";
    private static int adminID = 1000001;
    private int tblAdminId;
    private int tblAdminPersonId;
    public static List<Admin> admins = new ArrayList<>();
    private static int idAdmin = 1;

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
        this.id = id + adminID;
        adminID ++;
        this.tblAdminId = idAdmin;
        idAdmin ++;
        this.tblAdminPersonId = getTblPersonId();
    }

    public Admin() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTblAdminId() {
        return tblAdminId;
    }

    public void setTblAdminId(int tblAdminId) {
        this.tblAdminId = tblAdminId;
    }

    public int getTblAdminPersonId() {
        return tblAdminPersonId;
    }

    public void setTblAdminPersonId(int tblAdminPersonId) {
        this.tblAdminPersonId = tblAdminPersonId;
    }

    @Override
    public String toString() {
        return "Admin {" +
                "tblAdminId = " + getTblAdminId() +
                ", tblAdminPersonId = " + getTblAdminPersonId() +
                ", tblPersonId = " + getTblPersonId() +
                ", firstName = '" + getFirstName() + "'" +
                ", lastName = '" + getLastName() + "'" +
                ", username = '" + getUserName() + "'" +
                ", password = '" + getPassword() + "'" +
                ", id = " + getId() +
                "}";
    }

    public static void addAdmin(Admin admin) {
        DBUtils.insertAdmin(admin);
        admins = DBUtils.getTableAdminData();
    }

    public void runAdmin(){
        printQForExit();

        Scanner in = new Scanner(System.in);

        System.out.println("Would you like");
        System.out.println("1 - Register new user");
        System.out.println("2 - Print existing data from DB");

        String input = in.nextLine();

        switch (input) {
            case "Q", "q" -> {
                exitIfQ();
            }
            case "1" -> runRegistration();
            case "2" -> runPrintInformation();
        }
    }

    private void runRegistration() {
        System.out.println("Running Registration");
        printQForExit();

        Scanner in = new Scanner(System.in);

        System.out.println("Would you like");
        System.out.println("1 - Register new Student");
        System.out.println("2 - Register new Professor");
        System.out.println("3 - Register new Admin");

        String input = in.nextLine();

        switch (input) {
            case "Q", "q" -> {
                exitIfQ();
            }
            case "1" -> runRegisterNewStudent();
            case "2" -> runRegisterNewProfessor();
            case "3" -> runRegisterNewAdmin();
        }
    }

    private void runPrintInformation() {
        System.out.println("Running Print Information");
        printQForExit();
    }

    private void runRegisterNewStudent() {
        System.out.println("Register New Student");
        printQForExit();

        Scanner in = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String input = in.nextLine();
        exitIfQ(input);
        String firstName = input;

        System.out.print("Enter last name: ");
        input = in.nextLine();
        exitIfQ(input);
        String lastName = input;

        Student student = new Student(firstName, lastName);
        Student.addStudent(student);

        printStudentsList();


//        System.out.println("Would you like to register student for courses?");
//        System.out.println("1 - yes");
//        System.out.println("2 - no");
//        System.out.println("Q - for exit");
//        input = in.nextLine();
//
//        switch (input) {
//            case "Q", "q" -> {
//                System.out.println("Goodbye");
//                System.exit(0);
//            }
//            case "1" -> runCourseRegistration();
//            case "2" -> {
//                Student student1 = new Student(firstName, lastName, new ArrayList<>());
//                student.addStudent(student1);
//
//                runRegistration();
//            }
//        }

    }

    private void runRegisterNewProfessor() {

    }

    private void runRegisterNewAdmin() {

    }

    private void runCourseRegistration() {
        System.out.println("Register Student for Courses");
        Utils.printExitMessage();

        List<String> courses = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean flag = true;

        do {
            System.out.print("Enter course name OR 'Q' to quit: ");
            String input = in.nextLine();
            switch (input) {
                case "Q", "q" -> {
                    Utils.printList(courses);
                    flag = false;
                    runRegistration();

                }
                default ->  {
                    courses.add(input);
                    Utils.printList(courses);
                }
            }

        }
        while (flag);

    }



}
