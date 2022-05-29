package com.example.udhta_enl_app.HocTap.TvCDRauCu;

public class TvCDRauCu {
    String Tvraucu;
    String PhienamTVraucu;
    String NghiaTvraucu;
    String LinkanhTvraucu;

    public TvCDRauCu(){}

    public TvCDRauCu(String tvraucu, String phienamTVraucu, String nghiaTvraucu, String linkanhTvraucu) {
        Tvraucu = tvraucu;
        PhienamTVraucu = phienamTVraucu;
        NghiaTvraucu = nghiaTvraucu;
        LinkanhTvraucu = linkanhTvraucu;
    }

    public String getTvraucu() {
        return Tvraucu;
    }

    public void setTvraucu(String tvraucu) {
        Tvraucu = tvraucu;
    }

    public String getPhienamTVraucu() {
        return PhienamTVraucu;
    }

    public void setPhienamTVraucu(String phienamTVraucu) {
        PhienamTVraucu = phienamTVraucu;
    }

    public String getNghiaTvraucu() {
        return NghiaTvraucu;
    }

    public void setNghiaTvraucu(String nghiaTvraucu) {
        NghiaTvraucu = nghiaTvraucu;
    }

    public String getLinkanhTvraucu() {
        return LinkanhTvraucu;
    }

    public void setLinkanhTvraucu(String linkanhTvraucu) {
        LinkanhTvraucu = linkanhTvraucu;
    }
}
