package com.example.udhta_enl_app.TaiKhoan;

public class TaiKhoan {
    String id;
    String Name;
    String Email;
    String socaudung;
    String Diem;
    String Baikt;

    public TaiKhoan(){}

    public TaiKhoan(String id, String name, String email, String socaudung, String diem, String baikt) {
        this.id = id;
        Name = name;
        Email = email;
        this.socaudung = socaudung;
        Diem = diem;
        Baikt = baikt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSocaudung() {
        return socaudung;
    }

    public void setSocaudung(String socaudung) {
        this.socaudung = socaudung;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String diem) {
        Diem = diem;
    }

    public String getBaikt() {
        return Baikt;
    }

    public void setBaikt(String baikt) {
        Baikt = baikt;
    }
}
