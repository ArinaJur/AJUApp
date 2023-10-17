package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;

public abstract class Academic<T> extends Person<T> {
    private int tblAcademicId;
    private int course1;
    private int course2;
    private int course3;
    private int course4;
    private int course5;
    private int course6;
    public List<T> academics = new ArrayList<>();

    public Academic(String firstName, String lastName) {
        super(firstName, lastName);
        this.tblAcademicId = DBUtils.getLastId(Table.NAME.TBL_ACADEMIC) + 1;
    }

    public Academic(
            int tblPersonId, String firstName, String lastName, String userName, String password,
            int tblAcademicId, int course1, int course2, int course3, int course4, int course5, int course6
    ) {
        super(tblPersonId, firstName, lastName, userName, password);
        this.tblAcademicId = tblAcademicId;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.course6 = course6;
    }

    public Academic() {
    }

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

    public int getId() {
        return getTblAcademicId();
    }

    public T getTableData(Academic<T> person) {
        academics = DBUtils.getTableAcademicData(person);
        for(T academic : academics) {
            if (person.getTblPersonId() == person.getId()) {
                return academic;
            }
        }
        return null;
    }

    public abstract int getAcademicId();

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
