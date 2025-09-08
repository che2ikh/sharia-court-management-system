package Model;

import java.util.Date;

public  class Person {
    private  int id;
    private  String name;

    private String address,gender,phone;
    private Date birthdayDate;

    public Person(int id, String name, String address, String gender,String phone, Date birthdayDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phone=phone;
        this.birthdayDate = birthdayDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
}

