package com.example.fhospital;

public class docotor {

    public docotor(){}

    String fname;
    String email;
    String age;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    String gender;

    public docotor(String fname, String email, String age, String gender) {
        this.fname = fname;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}
