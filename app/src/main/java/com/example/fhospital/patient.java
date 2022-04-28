package com.example.fhospital;



public class patient {

    public patient(){}

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

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    String fname, email, age, blood, gender;

    public patient(String fname, String email, String age, String blood, String gender) {
        this.fname = fname;
        this.email = email;
        this.age = age;
        this.blood = blood;
        this.gender = gender;
    }
}
