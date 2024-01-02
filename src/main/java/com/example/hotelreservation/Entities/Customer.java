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

    @Override
    public String getValues() {
        return this.ID+","+"'"+this.username+"'"+","+"'"+this.password+"'"+","+"'"+this.mail+"'"+","+
                "'"+this.name+"'"+","+"'"+this.surname+"'"+","+"'"+this.identificationNumber+"'"+","+
                "'"+this.birthDate+"'"+","+"'"+this.phone+"'"+","+this.gender;
    }

    @Override
    public String getColumns() {
        return "ID,username,password,mail,name,surname,identificationNumber,birthDate,phone,gender";
    }
}
