package ajuapp;

import java.util.ArrayList;
import java.util.List;

public class Academic extends Person{
    private List<String> courses = new ArrayList<>();

    public Academic(String firstName, String lastName,  List<String> courses) {
        super(firstName, lastName);
        this.courses = courses;

    }
}
