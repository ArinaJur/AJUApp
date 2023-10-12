package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.TableName;

import java.util.List;

public class Academic extends Person {
    private int tblAcademicId;
    private int course1;
    private int course2;
    private int course3;
    private int course4;
    private int course5;
    private int course6;
    public static Academic academicForId = new Academic();

    public Academic(String firstName, String lastName, List<String> courses) {
        super(firstName, lastName);
        this.tblAcademicId = DBUtils.getLastIdFromTable(TableName.ACADEMIC) + 1;
        if (courses.size() != 0) {

        }
    }

    public Academic() {}

    public int getTblAcademicId() {
        return tblAcademicId;
    }

    public void setTblAcademicId(int tblAcademicId) {
        this.tblAcademicId = tblAcademicId;
    }

    public int getCourse1() {
        return course1;
    }

    public void setCourse1(int course1) {
        this.course1 = course1;
    }

    public int getCourse2() {
        return course2;
    }

    public void setCourse2(int course2) {
        this.course2 = course2;
    }

    public int getCourse3() {
        return course3;
    }

    public void setCourse3(int course3) {
        this.course3 = course3;
    }

    public int getCourse4() {
        return course4;
    }

    public void setCourse4(int course4) {
        this.course4 = course4;
    }

    public int getCourse5() {
        return course5;
    }

    public void setCourse5(int course5) {
        this.course5 = course5;
    }

    public int getCourse6() {
        return course6;
    }

    public void setCourse6(int course6) {
        this.course6 = course6;
    }

    public static void addCourses(int academicId, List<Integer> coursesIds) {
        Academic academic = DBUtils.getTableAcademicData(academicId);

        int[] dbIds = {academic.getCourse1(), academic.getCourse2(), academic.getCourse3(),
                academic.getCourse4(), academic.getCourse5(), academic.getCourse6()};

        for (int courseId : coursesIds) {
            for (int i = 0; i <= dbIds.length - 1; i++) {
                if (dbIds[i] == 0) {
                    dbIds[i] = courseId;
                    switch (i) {
                        case 0 -> academic.setCourse1(courseId);
                        case 1 -> academic.setCourse2(courseId);
                        case 2 -> academic.setCourse3(courseId);
                        case 3 -> academic.setCourse4(courseId);
                        case 4 -> academic.setCourse5(courseId);
                        case 5 -> academic.setCourse6(courseId);
                    }
                    break;
                }
            }
        }
        DBUtils.updateAcademicEnroll(academic);
        Student.students = DBUtils.getTableStudentData();
        //Professor.professors = DBUtils.getTableProfessorData();
    }

    public static String getFirstLastName(int academicId) {
        String firstLastNames = "";
        for (Student student : Student.students) {
            if (student.getTblStudentAcademicId() == academicId) {
                firstLastNames = student.getFirstName() + " " + student.getLastName();
            }
        }
//        if (!firstLastNames.isEmpty()) {
//            for (Professor professor : Professor.professors) {
//                if (professor.getTblProfessorAcademicId() == academicId) {
//                    firstLastNames = professor.getFirstName() + " " + professor.getLastName();
//                }
//            }
//        }

        if (firstLastNames.isEmpty()) {
            System.out.println("Invalid id");
        }

        return firstLastNames;
    }

    @Override
    public String toString() {
        return "Academic {\n" +
                "tblAcademicId = " + getTblAcademicId() + ",\n" +
                "course1 = '" + getCourse1() + "',\n" +
                "course2 = '" + getCourse2() + "',\n" +
                "course3 = '" + getCourse3() + "',\n" +
                "course4 = '" + getCourse4() + "',\n" +
                "course5 = '" + getCourse5() + "',\n" +
                "course6 = '" + getCourse6() + "',\n" +
                "},\n";
    }
}
