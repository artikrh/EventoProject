package com.ick.eventoproject;

public class User {
    String emri, mbiemri, email, ditelindja;
//Blank konstruktori per lexim te vlerave nga databaza
    public User() {

    }


    //Funksioni per ti ruajtur te dhenat ne databaze, paswordi ruhet te sektori per autentifikim
    public User(String emri, String mbiemri, String email, String ditelindja) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;

        this.ditelindja = ditelindja;
    }
}