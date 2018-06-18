package com.ick.eventoproject;

public class User {
    String emri, mbiemri, email;
//Blank konstruktori per lexim te vlerave nga databaza
    public User() {

    }


    //Funksioni per ti ruajtur te dhenat ne databaze, paswordi ruhet te sektori per autentifikim
    public User(String emri, String mbiemri, String email) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
    }
}