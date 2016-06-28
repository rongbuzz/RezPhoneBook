package com.example.user.rezphonebook;

/**
 * Created by User on 6/29/2016.
 */
public class ContactFolder {
    int id;
    String name;

    public ContactFolder(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public ContactFolder(String name, String phone) {

        this.name = name;
        this.phone = phone;
    }

    String phone;
}
