package com.example.udhta_enl_app.HocTap.TVCDLoaihoaActivity;

public class TVCDLoaihoa {
    String TuvungCDLH;
    String PhienamTVLH;
    String NghiaTVLH;
    String LinkanhTVLH;

    public TVCDLoaihoa(){
    }

    public TVCDLoaihoa(String tuvungCDLH, String phienamTVLH, String nghiaTVLH, String linkanhTVLH) {
        TuvungCDLH = tuvungCDLH;
        PhienamTVLH = phienamTVLH;
        NghiaTVLH = nghiaTVLH;
        LinkanhTVLH = linkanhTVLH;
    }

    public String getTuvungCDLH() {
        return TuvungCDLH;
    }

    public void setTuvungCDLH(String tuvungCDLH) {
        TuvungCDLH = tuvungCDLH;
    }

    public String getPhienamTVLH() {
        return PhienamTVLH;
    }

    public void setPhienamTVLH(String phienamTVLH) {
        PhienamTVLH = phienamTVLH;
    }

    public String getNghiaTVLH() {
        return NghiaTVLH;
    }

    public void setNghiaTVLH(String nghiaTVLH) {
        NghiaTVLH = nghiaTVLH;
    }

    public String getLinkanhTVLH() {
        return LinkanhTVLH;
    }

    public void setLinkanhTVLH(String linkanhTVLH) {
        LinkanhTVLH = linkanhTVLH;
    }
}
