package com.example.otelreservation.Entities;

public class Customer implements IEntity{
    private String ID;
    private String username;
    private String password;
    private String mail;
    private String name;
    private String surname;
    private String identificationNumber;
    private String birthDate;

    public Customer(String ID, String username, String password, String mail, String name, String surname, String identificationNumber, String birthDate) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.identificationNumber = identificationNumber;
        this.birthDate = birthDate;
    }

    @Override
    public String getValues() {
        return "'"+this.ID+"'"+","+"'"+this.username+"'"+","+"'"+this.password+"'"+","+"'"+this.mail+"'"+","+"'"+
                this.name+"'"+","+"'"+this.surname+"'"+","+"'"+this.identificationNumber+"'"+","+"'"+this.birthDate+"'";
    }

    @Override
    public String getColumns() {
        return "ID,username,password,mail,name,surname,identificationNumber,birthDate";
    }
}
