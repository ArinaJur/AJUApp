package ajuapp.database;

public final class Table {
    public enum TBL_PERSON {PERSON_ID,  FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD};
    public enum TBL_ADMIN {ADMIN_ID, PERSON_ID, ID};
    public enum TBL_STUDENT {STUDENT_ID, PERSON_ID, ACADEMIC_ID, ID};
    public enum TBL_PROFESSOR {PROFESSOR_ID, PERSON_ID, ACADEMIC_ID, ID};
    public enum TBL_ACADEMIC {ACADEMIC_ID, COURSE1_ID, COURSE2_ID, COURSE3_ID, COURSE4_ID, COURSE5_ID, COURSE6_ID};
    public enum TBL_COURSE {COURSE_ID, COURSE_NAME, PRICE};
    public enum NAME {TBL_PERSON, TBL_ADMIN, TBL_STUDENT, TBL_PROFESSOR, TBL_ACADEMIC, TBL_COURSE}

    public static String getId(Table.NAME name) {
        final String  tableName = name.toString();
        String id = "";
        switch (tableName) {
            case "TBL_PERSON" -> id = TBL_PERSON.PERSON_ID.toString();
            case "TBL_ADMIN" -> id = TBL_ADMIN.ADMIN_ID.toString();
            case "TBL_STUDENT" -> id = TBL_STUDENT.STUDENT_ID.toString();
            case "TBL_PROFESSOR" -> id = TBL_PROFESSOR.PROFESSOR_ID.toString();
            case "TBL_ACADEMIC" -> id = TBL_ACADEMIC.ACADEMIC_ID.toString();
            case "TBL_COURSE" -> id = TBL_COURSE.COURSE_ID.toString();
        }
        return id;
    }

}


