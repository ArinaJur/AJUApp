package ajuapp.database;

import ajuapp.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    private static final String SELECT_USERS = "SELECT * FROM tbl_person";
    private static final String SELECT_ADMINS = "SELECT * FROM tbl_admin";
    private static final String SELECT_STUDENTS = "SELECT * FROM tbl_student";
    private static final String SELECT_PROFESSORS = "SELECT * FROM tbl_professor";
    private static final String SELECT_ACADEMICS = "SELECT * FROM tbl_academic";
    private static final String SELECT_COURSES = "SELECT * FROM tbl_course";

    private static final String SELECT_LAST_PERSON_ID = "SELECT max(person_id) FROM tbl_person";
    private static final String SELECT_LAST_ADMIN_ID = "SELECT max(admin_id) FROM tbl_admin";
    private static final String SELECT_LAST_STUDENT_ID = "SELECT max(student_id) FROM tbl_student";
    private static final String SELECT_LAST_PROFESSOR_ID = "SELECT max(professor_id) FROM tbl_professor";
    private static final String SELECT_LAST_ACADEMIC_ID = "SELECT max(academic_id) FROM tbl_academic";
    private static final String SELECT_LAST_COURSE_ID = "SELECT max(course_id) FROM tbl_course";

    private static final String INSERT_PERSON =
            "INSERT INTO tbl_person(person_id, firstName, lastName, userName, password) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_ADMIN =
            "INSERT INTO tbl_admin(admin_id, person_id, role_id) VALUES (?, ?, ?);";
    private static final String INSERT_STUDENT =
            "INSERT INTO tbl_student(student_id, person_id, academic_id, role_id) VALUES (?, ?, ?, ?);";
    private static final String INSERT_ACADEMIC_EMPTY_ENROLL =
            "INSERT INTO tbl_academic(academic_id) VALUES (?);";
    private static final String INSERT_COURSE = "INSERT INTO tbl_course(course_id, course_name, price) VALUES (?, ?, ?);";

    private static final String DROP_TBL_PERSON = "DROP TABLE IF EXISTS tbl_person";
    private static final String DROP_TBL_ADMIN = "DROP TABLE IF EXISTS tbl_admin";
    private static final String DROP_TBL_STUDENT = "DROP TABLE IF EXISTS tbl_student";
    private static final String DROP_TBL_PROFESSOR = "DROP TABLE IF EXISTS tbl_professor";
    private static final String DROP_TBL_ACADEMIC = "DROP TABLE IF EXISTS tbl_academic";
    private static final String DROP_TBL_COURSE = "DROP TABLE IF EXISTS tbl_course";

    public static void dropTable(TableName tableName) {
        String query = "";
        switch (tableName) {
            case PERSON -> query = DROP_TBL_PERSON;
            case ADMIN -> query = DROP_TBL_ADMIN;
            case STUDENT -> query = DROP_TBL_STUDENT;
            case PROFESSOR -> query = DROP_TBL_PROFESSOR;
            case ACADEMIC -> query = DROP_TBL_ACADEMIC;
            case COURSE -> query = DROP_TBL_COURSE;
        }
        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLastIdFromTable(TableName tableName) {
        String query = "";
        int lastId = 0;
        switch (tableName) {
            case PERSON -> query = SELECT_LAST_PERSON_ID;
            case ADMIN -> query = SELECT_LAST_ADMIN_ID;
            case STUDENT -> query = SELECT_LAST_STUDENT_ID;
            case PROFESSOR -> query = SELECT_LAST_PROFESSOR_ID;
            case ACADEMIC -> query = SELECT_LAST_ACADEMIC_ID;
            case COURSE -> query = SELECT_LAST_COURSE_ID;
        }
        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statementQuery = connection.prepareStatement(query);
        ) {

            ResultSet resultQuery = statementQuery.executeQuery();

            while (resultQuery.next()) {
                lastId = resultQuery.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }

    public static List<Person> getTablePersonData() {
        List<Person> dbUsers = new ArrayList<>();

        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_USERS);
        ) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int person_id = result.getInt("person_id");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String userName = result.getString("userName");
                String password = result.getString("password");

                Person person = new Admin();

                person.setTblPersonId(person_id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setUserName(userName);
                person.setPassword(password);

                dbUsers.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbUsers;
    }

    public static List<Admin> getTableAdminData() {
        List<Admin> dbAdmins = new ArrayList<>();

        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statementPerson = connection.prepareStatement(SELECT_USERS);
                PreparedStatement statementAdmin = connection.prepareStatement(SELECT_ADMINS);
        ) {

            ResultSet resultPerson = statementPerson.executeQuery();
            ResultSet resultAdmin = statementAdmin.executeQuery();

            while (resultAdmin.next()) {
                int tblAdminId = resultAdmin.getInt("admin_id");
                int tblAdminPersonId = resultAdmin.getInt("person_id");
                String roleId = resultAdmin.getString("role_id");

                while (resultPerson.next()) {
                    int tblPersonId = resultPerson.getInt("person_id");

                    if (tblAdminPersonId == tblPersonId) {
                        String firstName = resultPerson.getString("firstName");
                        String lastName = resultPerson.getString("lastName");
                        String userName = resultPerson.getString("userName");
                        String password = resultPerson.getString("password");

                        Admin admin = new Admin();
                        admin.setTblAdminId(tblAdminId);
                        admin.setTblAdminPersonId(tblAdminPersonId);
                        admin.setRoleId(roleId);

                        admin.setTblPersonId(tblPersonId);
                        admin.setFirstName(firstName);
                        admin.setLastName(lastName);
                        admin.setUserName(userName);
                        admin.setPassword(password);

                        dbAdmins.add(admin);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbAdmins;
    }

    public static List<Student> getTableStudentData() {
        List<Student> dbStudent = new ArrayList<>();

        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statementPerson = connection.prepareStatement(SELECT_USERS);
                PreparedStatement statementStudent = connection.prepareStatement(SELECT_STUDENTS);
                PreparedStatement statementAcademic = connection.prepareStatement(SELECT_ACADEMICS);
        ) {

            ResultSet resultPerson = statementPerson.executeQuery();
            ResultSet resultStudent = statementStudent.executeQuery();
            ResultSet resultAcademic = statementAcademic.executeQuery();

            while (resultStudent.next()) {
                int tblStudentId = resultStudent.getInt("student_id");
                int tblStudentPersonId = resultStudent.getInt("person_id");
                int tblStudentAcademicId = resultStudent.getInt("academic_id");
                String roleId = resultStudent.getString("role_id");

                while (resultPerson.next()) {
                    int tblPersonId = resultPerson.getInt("person_id");

                    if (tblStudentPersonId == tblPersonId) {
                        String firstName = resultPerson.getString("firstName");
                        String lastName = resultPerson.getString("lastName");
                        String userName = resultPerson.getString("userName");
                        String password = resultPerson.getString("password");

                        while (resultAcademic.next()) {
                            int tblAcademicId = resultAcademic.getInt("academic_id");

                            if (tblStudentAcademicId == tblAcademicId) {
                                int course1Id = resultAcademic.getInt("course1_id");
                                int course2Id = resultAcademic.getInt("course2_id");
                                int course3Id = resultAcademic.getInt("course3_id");
                                int course4Id = resultAcademic.getInt("course4_id");
                                int course5Id = resultAcademic.getInt("course5_id");
                                int course6Id = resultAcademic.getInt("course6_id");

                                Student student = new Student();
                                student.setTblStudentId(tblStudentId);
                                student.setTblStudentPersonId(tblStudentPersonId);
                                student.setTblStudentAcademicId(tblStudentAcademicId);
                                student.setRoleId(roleId);

                                student.setTblPersonId(tblPersonId);
                                student.setFirstName(firstName);
                                student.setLastName(lastName);
                                student.setUserName(userName);
                                student.setPassword(password);

                                student.setTblAcademicId(tblAcademicId);
                                student.setCourse1(course1Id);
                                student.setCourse2(course2Id);
                                student.setCourse3(course3Id);
                                student.setCourse4(course4Id);
                                student.setCourse5(course5Id);
                                student.setCourse6(course6Id);

                                dbStudent.add(student);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbStudent;
    }

    public static List<Course> getTableCourseData() {
        List<Course> dbCourse = new ArrayList<>();

        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statementCourse = connection.prepareStatement(SELECT_COURSES);
        ) {

            ResultSet resultCourse = statementCourse.executeQuery();

            while (resultCourse.next()) {
                int tblCourseId = resultCourse.getInt("course_id");
                String courseName = resultCourse.getString("course_name");
                int price = resultCourse.getInt("price");

                Course course = new Course();
                course.setTblCourseId(tblCourseId);
                course.setCourseName(courseName);
                course.setPrice(price);

                dbCourse.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbCourse;
    }

    public static Academic getTableAcademicData(int academicId) {
        Academic dbAcademic = new Academic();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementSelectAcademics = connection.prepareStatement(SELECT_ACADEMICS);
        ) {

            ResultSet resultAcademic = statementSelectAcademics.executeQuery();

            while (resultAcademic.next()) {
                int tblAcademicId = resultAcademic.getInt("academic_id");

                if (academicId == tblAcademicId) {
                    int course1Id = resultAcademic.getInt("course1_id");
                    int course2Id = resultAcademic.getInt("course2_id");
                    int course3Id = resultAcademic.getInt("course3_id");
                    int course4Id = resultAcademic.getInt("course4_id");
                    int course5Id = resultAcademic.getInt("course5_id");
                    int course6Id = resultAcademic.getInt("course6_id");

                    dbAcademic.setTblAcademicId(tblAcademicId);
                    dbAcademic.setCourse1(course1Id);
                    dbAcademic.setCourse2(course2Id);
                    dbAcademic.setCourse3(course3Id);
                    dbAcademic.setCourse4(course4Id);
                    dbAcademic.setCourse5(course5Id);
                    dbAcademic.setCourse6(course6Id);

                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbAcademic;
    }


    public static void insertAdmin(Admin admin) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(INSERT_PERSON);
             PreparedStatement statementAdmin = connection.prepareStatement(INSERT_ADMIN);
        ) {
            statementPerson.setInt(1, admin.getTblPersonId());
            statementPerson.setString(2, admin.getFirstName());
            statementPerson.setString(3, admin.getLastName());
            statementPerson.setString(4, admin.getUserName());
            statementPerson.setString(5, admin.getPassword());

            statementPerson.executeUpdate();

            statementAdmin.setInt(1, admin.getTblAdminId());
            statementAdmin.setInt(2, admin.getTblAdminPersonId());
            statementAdmin.setString(3, admin.getRoleId());

            statementAdmin.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(Student student) {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statementPerson = connection.prepareStatement(INSERT_PERSON);
             PreparedStatement statementStudent = connection.prepareStatement(INSERT_STUDENT);
             PreparedStatement statementAcademic = connection.prepareStatement(INSERT_ACADEMIC_EMPTY_ENROLL);) {
            statementPerson.setInt(1, student.getTblPersonId());
            statementPerson.setString(2, student.getFirstName());
            statementPerson.setString(3, student.getLastName());
            statementPerson.setString(4, student.getUserName());
            statementPerson.setString(5, student.getPassword());

            statementPerson.executeUpdate();

            statementAcademic.setInt(1, student.getTblAcademicId());

            statementAcademic.executeUpdate();

            statementStudent.setInt(1, student.getTblStudentId());
            statementStudent.setInt(2, student.getTblStudentPersonId());
            statementStudent.setInt(3, student.getTblStudentAcademicId());
            statementStudent.setString(4, student.getRoleId());

            statementStudent.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCourse(Course course) {
        try (
                Connection connection = DBConnect.getConnection();
                PreparedStatement statementCourse = connection.prepareStatement(INSERT_COURSE);
        ) {

            statementCourse.setInt(1, course.getTblCourseId());
            statementCourse.setString(2, course.getCourseName());
            statementCourse.setInt(3, course.getPrice());

            statementCourse.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAcademicEnroll(Academic academic) {
        try (Connection connection = DBConnect.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ) {

            int idAcademic = academic.getTblAcademicId();
            String query = SELECT_ACADEMICS + " WHERE academic_id = " + idAcademic;

            ResultSet resultAcademic = statement.executeQuery(query);//Select * From Users Where Id = 123

            while (resultAcademic.next()) {
                int tblAcademicId = resultAcademic.getInt("academic_id");

                if (idAcademic == tblAcademicId) {
                    resultAcademic.updateInt("course1_id", academic.getCourse1());
                    resultAcademic.updateInt("course2_id", academic.getCourse2());
                    resultAcademic.updateInt("course3_id", academic.getCourse3());
                    resultAcademic.updateInt("course4_id", academic.getCourse4());
                    resultAcademic.updateInt("course5_id", academic.getCourse5());
                    resultAcademic.updateInt("course6_id", academic.getCourse6());

                    resultAcademic.updateRow();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
