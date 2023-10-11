package ajuapp;

import ajuapp.database.DBUtils;
import ajuapp.database.Tables;

public abstract class Person {
    private int tblPersonId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int userNameId = tblPersonId + 1097523;

    public Person(String firstName, String lastName) {
        int lastTblPersonId = DBUtils.getLastIdFromTable(Tables.PERSON);
        this.tblPersonId = lastTblPersonId + 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = String.valueOf(userNameId);
        generateUsername(firstName, lastName);
        userNameId ++;
    }

    public Person() {};

    private void generateUsername(String firstName, String lastName) {
        this.userName = firstName.trim().charAt(0) + lastName.trim() + userNameId;
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

    public int getUserNameId() {
        return userNameId;
    }

    public void setUserNameId(int userNameId) {
        this.userNameId = userNameId;
    }

    @Override
    public String toString() {
        return "Person {\n" +
                "tblPersonId = " + getTblPersonId() + ",\n" +
                "firstName = '" + getFirstName() + "',\n" +
                "lastName = '" + getLastName() + "',\n" +
                "username = '" + getUserName() + "',\n" +
                "password = '" + getPassword() + "',\n" +
                "}";
    }

}
