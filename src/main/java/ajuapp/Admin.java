package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ajuapp.Course.courses;
import static ajuapp.Professor.professors;
import static ajuapp.Student.students;

public final class Admin extends Person<Admin> implements IPrintAdmin, IExit, IPrintAcademic {
    private int tblAdminId;
    private int tblAdminPersonId;
    private String roleId = "A";
    public static List<Admin> admins = new ArrayList<>();

    public Admin(String firstName, String lastName) {
        super(firstName, lastName);
        final int lastTblAdminId = DBUtils.getLastId(Table.NAME.TBL_ADMIN);
        this.tblAdminId = lastTblAdminId + 1;
        this.tblAdminPersonId = getTblPersonId();
        this.roleId = roleId + 10000 + tblAdminId;
    }

    public Admin(
            int tblPersonId, String firstName, String lastName, String userName, String password,
            int tblAdminId, int tblAdminPersonId, String roleId
    ) {
        super(tblPersonId, firstName, lastName, userName, password);
        this.tblAdminId = tblAdminId;
        this.tblAdminPersonId = tblAdminPersonId;
        this.roleId = roleId;
    }

    public Admin(int tblPersonId, String firstName, String lastName, String userName, String password) {
        super(tblPersonId, firstName, lastName, userName, password);
    }

    public Admin(){}

    public int getTblAdminId() {
        return tblAdminId;
    }

    public int getTblAdminPersonId() {
        return tblAdminPersonId;
    }

    public String getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "\nAdmin {\n" +
                "tblAdminId = " + getTblAdminId() + ",\n" +
                "tblPersonId = " + getTblPersonId() + ",\n" +
                "tblAdminPersonId = " + getTblAdminPersonId() + ",\n" +
                "roleId = " + getRoleId() + ",\n" +
                "firstName = '" + getFirstName() + "',\n" +
                "lastName = '" + getLastName() + "',\n" +
                "username = '" + getUserName() + "',\n" +
                "password = '" + getPassword() + "',\n" +
                "}";
    }

    @Override
    public char getRole() {
        return roleId.charAt(0);
    }

    @Override
    public Admin getTableData(int id) {
        admins = DBUtils.getTableAdminData();
        for (Admin admin : admins) {
            if (admin.getTblAdminId() == id) {
                return admin;
            }
        }

        return null;
    }

    public static void addAdmin(Admin admin) {
        DBUtils.insertAdmin(admin);
        admins = DBUtils.getTableAdminData();
    }

    private void addStudent(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
        DBUtils.insertStudent(student);
        students = DBUtils.getTableStudentData();
    }

    private void addProfessor(String firstName, String lastName) {

    }

    private Student getLastStudent() {
        return students.get(students.size() - 1);
    }

    private Professor getLastProfessor() {
        return professors.get(professors.size() - 1);
    }

    private void addCourse(Course course) {
        DBUtils.insertCourse(course);
        courses = DBUtils.getTableCourseData();
    }

    public <T> void registerForCourses(Academic<T> person, List<Integer> coursesIds) {
        final int[] dbIds = {person.getCourse1(), person.getCourse2(), person.getCourse3(),
                person.getCourse4(), person.getCourse5(), person.getCourse6()};

        for (int courseId : coursesIds) {
            for (int i = 0; i <= dbIds.length - 1; i++) {
                if (dbIds[i] == 0) {
                    dbIds[i] = courseId;
                    switch (i) {
                        case 0 -> person.setCourse1(courseId);
                        case 1 -> person.setCourse2(courseId);
                        case 2 -> person.setCourse3(courseId);
                        case 3 -> person.setCourse4(courseId);
                        case 4 -> person.setCourse5(courseId);
                        case 5 -> person.setCourse6(courseId);
                    }
                    break;
                }
            }
        }
        DBUtils.updateAcademicEnroll(person);
        Student.students = DBUtils.getTableStudentData();
        Professor.professors = DBUtils.getTableProfessorData();
    }

    private void runAdminIfQ(String input) {
        if (input.equalsIgnoreCase("Q" )) {
            runAdmin();
        }
    }

    public void runAdmin() {
        System.out.println("Running System Administration" );

        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Register new user" );
        System.out.println("2 - Add new course" );
        System.out.println("3 - Register Student/Professor for Course" );
        System.out.println("4 - Print existing data from DB" );

        String input = in.nextLine();

        switch (input) {
            case "Q", "q" -> new SignUp().runAJUApp();
            case "1" -> runRegistration();
            case "2" -> runAddCourse();
            case "3" -> runRegisterForCourse();
            case "4" -> runPrintInformation();
        }
    }

    private void runRegistration() {
        System.out.println("\n\n\nRunning Registration" );

        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Register new Student" );
        System.out.println("2 - Register new Professor" );
        System.out.println("3 - Register new Admin" );

        String input = in.nextLine();

        switch (input) {
            case "Q", "q" -> runAdmin();
            case "1" -> runRegisterNewStudent();
            case "2" -> runRegisterNewProfessor();
            case "3" -> runRegisterNewAdmin();
        }
    }

    private void runAddCourse() {
        printCoursesList();

        System.out.println("\n\nAdd New Course" );

        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.print("Enter course name: " );
        String input = in.nextLine();
        runAdminIfQ(input);
        final String courseName = input;

        System.out.print("Enter course price (whole number): " );
        input = in.nextLine();
        runAdminIfQ(input);
        String coursePrice = input;
        final int price = Integer.parseInt(coursePrice);

        addCourse(new Course(courseName, price));

        printQForExit();
        System.out.println("Would you like to add another course?" );
        System.out.println("1 - yes" );
        System.out.println("2 - no" );
        input = in.nextLine();

        switch (input) {
            case "Q", "q", "2" -> runAdmin();
            case "1" -> runAddCourse();
        }
    }

    private void runPrintInformation() {
        System.out.println("\n\nRunning Print Information" );

        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.println("Would you like" );
        System.out.println("1 - Print Admins" );
        System.out.println("2 - Print Students" );
        System.out.println("3 - Print Professors" );
        System.out.println("4 - Print Courses" );

        String input = in.nextLine();

        switch (input) {
            case "Q", "q" -> runAdmin();
            case "1" -> runPrintAdminsList();
            case "2" -> runPrintStudentsList();
            case "3" -> runPrintProfessorsList();
            case "4" -> runPrintCoursesList();
        }
    }

    private void runPrintAdminsList() {
        System.out.println("\n\n\nAdmins List:" );
        printAdminsList();
        runAdmin();
    }

    private void runPrintStudentsList() {
        System.out.println("\n\n\nStudents List:" );
        printStudentsList();
        runAdmin();
    }

    private void runPrintProfessorsList() {
        System.out.println("\n\n\nProfessors List:" );
        printProfessorsList();
        runAdmin();
    }

    private void runPrintCoursesList() {
        System.out.println("\n\n\nCourses List:" );
        printCoursesList();
        runAdmin();
    }

    private String[] scanFirstAndLastNames() {
        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.print("Enter first name: " );
        String input = in.nextLine();
        runAdminIfQ(input);
        final String firstName = input;

        System.out.print("Enter last name: " );
        input = in.nextLine();
        runAdminIfQ(input);
        final String lastName = input;

        return new String[]{firstName, lastName};
    }

    private void runRegisterNewStudent() {
        System.out.println("\n\nRegister New Student" );

        final String[] data = scanFirstAndLastNames();
        addStudent(data[0], data[1]);

        System.out.println("New Student registered:" );
        printLastStudent();
        scanRegisterForCourse(getLastStudent());
    }

    private void runRegisterNewProfessor() {
        System.out.println("\n\nRegister New Professor" );

        final String[] data = scanFirstAndLastNames();
        addProfessor(data[0], data[1]);

        System.out.println("New Professor registered:" );
        printLastProfessor();
        scanRegisterForCourse(getLastProfessor());
    }

    private void runRegisterNewAdmin() {
        System.out.println("\n\nRegister New Admin" );

        final String[] data = scanFirstAndLastNames();
        addAdmin(new Admin(data[0], data[1]));

        System.out.println("New Admin registered:" );
        printLastAdmin();
        runRegistration();
    }

    private <T> void scanRegisterForCourse(Academic<T> person) {
        Scanner in = new Scanner(System.in);

        printQForExit();

        switch (person.getRole()) {
            case 'S' -> System.out.println(
                    "\n\nWould you like to register Student "
                            + person.getFirstName() + " " + person.getLastName()
                            + " for courses?"
            );
            case 'P' -> System.out.println(
                    "\n\nWould you like to assign Professor "
                            + person.getFirstName() + " " + person.getLastName()
                            + " to teach courses?"
            );
        }

        System.out.println("1 - yes" );
        System.out.println("2 - no" );
        String input = in.nextLine();

        switch (input) {
            case "Q", "q", "2" -> runRegistration();
            case "1" -> runCourseRegistration(person);
        }
    }

    private void runRegisterForCourse() {
        System.out.println("\n\n\nRegister for Course");
        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.println("Would you like to:" );
        System.out.println("1 - Register Student for courses" );
        System.out.println("2 - Assign Professor to teach courses" );

        String input = in.nextLine();
        switch (input) {
            case "Q", "q" -> runAdmin();
            case "1" -> runStudentCourseRegistration();
            case "2" -> runProfessorCourseAssignment();
        }
    }

    private void runStudentCourseRegistration() {
        System.out.println("\n\n\n" );
        Scanner in = new Scanner(System.in);

        printQForExit();
        System.out.println("Would you like to:" );
        System.out.println("1 - Input Student Id" );
        System.out.println("2 - Print Students List" );

        String input = in.nextLine();
        switch (input) {
            case "Q", "q" -> runRegisterForCourse();
            case "1" -> runConfirmStudent();
            case "2" -> {
                printStudentsList();
                runStudentCourseRegistration();
            }
        }
    }

    private void runConfirmStudent() {
        Scanner in = new Scanner(System.in);
        printQForExit();
        System.out.println();
        System.out.print("Enter Student Id: ");

        String input = in.nextLine();

        int id = Integer.parseInt(input);
        Student student = new Student().getTableData(id);
        System.out.println(student);
        System.out.println();

        printQForExit();
        System.out.println("Is this information about the Student correct?");
        System.out.println("1 - Confirm Student" );
        System.out.println("2 - Wrong Student" );

        input = in.nextLine();
        switch (input) {
            case "Q", "q" -> runRegisterForCourse();
            case "1" -> {
                assert student != null;
                runCourseRegistration(student);
            }
            case "2" -> {
                System.out.println("\n");
                printStudentsList();
                System.out.print("Enter Student Id: " );
            }
        }
    }

    private void runProfessorCourseAssignment() {

    }

    private <T> void runCourseRegistration(Academic<T> person) {
        int count = person.getRole() == 'S' ? 6 : 3;

        List<Integer> coursesIds = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        printCoursesList();
        printQForExit();
        do {
            System.out.print("Enter course id: " );
            String input = in.nextLine();
            if (input.equalsIgnoreCase("Q" ) && coursesIds.size() == 0) {
                runAdmin();
            } else if (input.equalsIgnoreCase("Q" ) && coursesIds.size() != 0) {
                registerForCourses(person, coursesIds);
                printStudentsList();
                runRegistration();
            } else {
                courses = DBUtils.getTableCourseData();
                for (Course course : courses) {
                    if (Integer.parseInt(input) == course.getTblCourseId()) {
                        coursesIds.add(course.getTblCourseId());
                    }
                }
            }
            count--;
        } while (count != 0);
    }
}
