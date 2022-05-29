package com.example.udhta_enl_app.Kiemtra;

public class DiemKT {
    String name;
    String Tongdiem;
    String correct;

    public DiemKT(String name, String tongdiem, String correct) {
        this.name = name;
        Tongdiem = tongdiem;
        this.correct = correct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTongdiem() {
        return Tongdiem;
    }

    public void setTongdiem(String tongdiem) {
        Tongdiem = tongdiem;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
