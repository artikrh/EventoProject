package com.ick.eventoproject;

public class Business_register {
   String  eventName, eventDesciption,category;
    String date;


    //Blank konstruktori per lexim te vlerave nga databaza
    public Business_register() {

    }


    //Funksioni per ti ruajtur te dhenat ne databaze, paswordi ruhet te sektori per autentifikim
    public Business_register(String eventName, String eventDesciption, String  category, String date) {
        this.eventName = eventName;
        this.eventDesciption = eventDesciption;
        this.category = category;
        this.date = date;
    }
}