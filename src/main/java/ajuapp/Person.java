package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Table;

import java.util.ArrayList;
import java.util.List;

public abstract class Person<T> {
    private int tblPersonId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    public List<Admin> users = new ArrayList<>();

    public Person() {};

    public Person(String firstName, String lastName) {
        final int maxPersonId = DBUtils.getLastId(Table.NAME.TBL_PERSON);
        this.tblPersonId = maxPersonId + 1;
        final int userNameId = tblPersonId + 1097523;
        this.firstName = capitalizeString(firstName);
        this.lastName = capitalizeString(lastName);
        this.userName = generateUsername(userNameId);
        this.password = generatePassword(userNameId);
    }

    public Person(int tblPersonId, String firstName, String lastName, String userName, String password) {
        this.tblPersonId = tblPersonId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    private String capitalizeString(String string) {
        return string.trim().toUpperCase().charAt(0) + string.substring(1).toLowerCase();
    }

    private String generateUsername(int userNameId) {
        return getFirstName().trim().charAt(0) + getLastName().trim() + userNameId;
    }

    private String generatePassword(int userNameId) {
        final String fLetter = String.valueOf(getFirstName().trim().toLowerCase().charAt(0));
        final String sLetter = String.valueOf(getLastName().toUpperCase().trim().charAt(0));
        return fLetter + sLetter + (userNameId / 9);
    }

    public int getTblPersonId() {
        return tblPersonId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public abstract char getRole();

    public T getTableData(int id) {
        users = DBUtils.getTablePersonData();
        for(Admin user : users) {
            if (user.getTblPersonId() == id) {
                return (T)user;
            }
        }
        return null;
    };

    public int getId() {
        return getTblPersonId();
    }

    @Override
    public String toString() {
        return "Person {\n" +
                "tblPersonId = " + getTblPersonId() + ",\n" +
                "firstName = '" + getFirstName() + "',\n" +
                "lastName = '" + getLastName() + "',\n" +
                "username = '" + getUserName() + "',\n" +
                "password = '" + getPassword() + "',\n" +
                "},\n";
    }
}
