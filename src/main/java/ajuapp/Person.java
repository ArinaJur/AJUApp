package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.TableName;

public abstract class Person {
    private int tblPersonId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public Person(String firstName, String lastName) {
        int lastTblPersonId = DBUtils.getLastIdFromTable(TableName.PERSON);
        this.tblPersonId = lastTblPersonId + 1;
        int userNameId = tblPersonId + 1097523;
        this.firstName = capitalizeString(firstName);
        this.lastName = capitalizeString(lastName);
        this.userName = generateUsername(userNameId);
        this.password = generatePassword(userNameId);
    }

    public Person() {};

    private String capitalizeString(String string) {
        return string.trim().toUpperCase().charAt(0) + string.substring(1).toLowerCase();
    }

    private String generateUsername(int userNameId) {
        return getFirstName().trim().charAt(0) + getLastName().trim() + userNameId;
    }

    private String generatePassword(int userNameId) {
        String fLetter = String.valueOf(getFirstName().trim().toLowerCase().charAt(0));
        String sLetter = String.valueOf(getLastName().toUpperCase().trim().charAt(0));
        return  fLetter + sLetter + (userNameId / 9);
    }

    public int getTblPersonId() {
        return tblPersonId;
    }

    public void setTblPersonId(int tblPersonId) {
        this.tblPersonId = tblPersonId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
