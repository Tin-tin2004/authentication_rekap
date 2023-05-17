package com.group_one.authentication_rekap;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class  User {
    private String title;
    private String notes;

    public User() {
    }

    public User(String name, String notes) {
        this.title = name;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }
}
