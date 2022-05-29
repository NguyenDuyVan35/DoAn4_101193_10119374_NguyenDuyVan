package com.example.udhta_enl_app.HocTap.CDGiadinh;

public class TvCDGiadinh {
    private String TuVung;
    private String PhienAm;
    private String Nghia;
    private String LinkAnhtvCDGD;


    public TvCDGiadinh(){
    }

    public TvCDGiadinh(String tuVung, String phienAm, String nghia, String linkAnhtvCDGD) {
        TuVung = tuVung;
        PhienAm = phienAm;
        Nghia = nghia;
        LinkAnhtvCDGD = linkAnhtvCDGD;
    }

    public String getTuVung() {
        return TuVung;
    }

    public void setTuVung(String tuVung) {
        TuVung = tuVung;
    }

    public String getPhienAm() {
        return PhienAm;
    }

    public void setPhienAm(String phienAm) {
        PhienAm = phienAm;
    }

    public String getNghia() {
        return Nghia;
    }

    public void setNghia(String nghia) {
        Nghia = nghia;
    }

    public String getLinkAnhtvCDGD() {
        return LinkAnhtvCDGD;
    }

    public void setLinkAnhtvCDGD(String linkAnhtvCDGD) {
        LinkAnhtvCDGD = linkAnhtvCDGD;
    }
}
