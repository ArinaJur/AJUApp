package ajuapp;

import ajuapp.database.DBUtils;

import java.util.ArrayList;
import java.util.List;

public final class Professor extends Academic<Professor> {
    private String roleId = "P";
    public static List<Professor> professors = new ArrayList<>();

    @Override
    public char getRole() {
        return roleId.charAt(0);
    }

    @Override
    public Professor getTableData(int id) {
        return DBUtils.getTableProfessorData(id);
    }

    @Override
    public int getAcademicId() {
        return 0;
    }

    public void runProfessor(){

    }


}
