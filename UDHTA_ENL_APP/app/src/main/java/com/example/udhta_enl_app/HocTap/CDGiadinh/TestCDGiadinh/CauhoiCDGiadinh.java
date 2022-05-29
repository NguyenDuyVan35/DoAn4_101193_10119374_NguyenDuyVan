package com.example.udhta_enl_app.HocTap.CDGiadinh.TestCDGiadinh;

public class CauhoiCDGiadinh {
    String CauhoiCDGD;
    String DichCHCDGD;
    String Dapan1;
    String Dapan2;
    String Dapan3;
    String Dapan4;
    String Dapan;
    String LinkAnhMH;
    String GhiChuCH;

    public CauhoiCDGiadinh(){

    }

    public CauhoiCDGiadinh(String cauhoiCDGD, String dichCHCDGD, String dapan1, String dapan2, String dapan3, String dapan4, String dapan, String linkAnhMH, String ghiChuCH) {
        CauhoiCDGD = cauhoiCDGD;
        DichCHCDGD = dichCHCDGD;
        Dapan1 = dapan1;
        Dapan2 = dapan2;
        Dapan3 = dapan3;
        Dapan4 = dapan4;
        Dapan = dapan;
        LinkAnhMH = linkAnhMH;
        GhiChuCH = ghiChuCH;
    }

    public String getCauhoiCDGD() {
        return CauhoiCDGD;
    }

    public void setCauhoiCDGD(String cauhoiCDGD) {
        CauhoiCDGD = cauhoiCDGD;
    }

    public String getDichCHCDGD() {
        return DichCHCDGD;
    }

    public void setDichCHCDGD(String dichCHCDGD) {
        DichCHCDGD = dichCHCDGD;
    }

    public String getDapan1() {
        return Dapan1;
    }

    public void setDapan1(String dapan1) {
        Dapan1 = dapan1;
    }

    public String getDapan2() {
        return Dapan2;
    }

    public void setDapan2(String dapan2) {
        Dapan2 = dapan2;
    }

    public String getDapan3() {
        return Dapan3;
    }

    public void setDapan3(String dapan3) {
        Dapan3 = dapan3;
    }

    public String getDapan4() {
        return Dapan4;
    }

    public void setDapan4(String dapan4) {
        Dapan4 = dapan4;
    }

    public String getDapan() {
        return Dapan;
    }

    public void setDapan(String dapan) {
        Dapan = dapan;
    }

    public String getLinkAnhMH() {
        return LinkAnhMH;
    }

    public void setLinkAnhMH(String linkAnhMH) {
        LinkAnhMH = linkAnhMH;
    }

    public String getGhiChuCH() {
        return GhiChuCH;
    }

    public void setGhiChuCH(String ghiChuCH) {
        GhiChuCH = ghiChuCH;
    }
}