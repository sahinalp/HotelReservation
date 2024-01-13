package com.example.hotelreservation.Entities;

public class Customer implements IEntity{
    private int ID;
    private String username;
    private String password;
    private String mail;
    private String name;
    private String surname;
    private String identificationNumber;
    private String birthDate;
    private String phone;
    private  int gender;

    public Customer() {

    }
    public Customer(int ID, String username, String password, String mail, String name,
                    String surname, String identificationNumber, String birthDate, String phone, int gender) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.identificationNumber = identificationNumber;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isCustomer(String _username, String _password)
    {
        if((this.username.contentEquals(_username)) && (this.password.contentEquals(_password)))
        {
            return true;
        }
        return false;
    }

    @Override
    public String getValues() {
        return this.ID+","+"'"+this.username+"'"+","+"'"+this.password+"'"+","+"'"+this.mail+"'"+","+
                "'"+this.name+"'"+","+"'"+this.surname+"'"+","+"'"+this.identificationNumber+"'"+","+
                "'"+this.birthDate+"'"+","+"'"+this.phone+"'"+","+this.gender;
    }

    @Override
    public String getColumns() {
        return "\"ID\", username, \"password\", mail, \"name\", surname, \"identificationNumber\", \"birthDate\", phone, gender";
    }
}
