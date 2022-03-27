package com.example.benson.bean;

public class User {
    private int id;
    private String name;
    private String password;
    private boolean administrator;

    public User() {
    }

    public User(int id, String name, String password, boolean administrator) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.administrator = administrator;
    }

    public User(String name, String password) {
        this(0, name, password, false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", administrator=" + administrator
                + "]";
    }
}
