package com.ick.eventoproject;

public class User {
    String emri, mbiemri, email;
    boolean profile_type;
//Blank konstruktori per lexim te vlerave nga databaza
    public User() {

    }


    //Funksioni per ti ruajtur te dhenat ne databaze, paswordi ruhet te sektori per autentifikim
    public User(String emri, String mbiemri, String email, boolean profile_type) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.profile_type=profile_type;
    }
}