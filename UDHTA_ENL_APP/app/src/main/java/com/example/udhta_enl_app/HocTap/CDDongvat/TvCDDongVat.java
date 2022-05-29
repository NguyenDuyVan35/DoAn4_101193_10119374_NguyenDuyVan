package com.example.udhta_enl_app.HocTap.CDDongvat;

public class TvCDDongVat {
    private String TuVungDV;
    private String PhienAmTVDV;
    private String NghiaTVDV;
    private String LinkAnhtvCDDV;


    public TvCDDongVat(){
    }

    public TvCDDongVat(String tuVungDV, String phienAmTVDV, String nghiaTVDV, String linkAnhtvCDDV) {
        TuVungDV = tuVungDV;
        PhienAmTVDV = phienAmTVDV;
        NghiaTVDV = nghiaTVDV;
        LinkAnhtvCDDV = linkAnhtvCDDV;
    }

    public String getTuVungDV() {
        return TuVungDV;
    }

    public void setTuVungDV(String tuVungDV) {
        TuVungDV = tuVungDV;
    }

    public String getPhienAmTVDV() {
        return PhienAmTVDV;
    }

    public void setPhienAmTVDV(String phienAmTVDV) {
        PhienAmTVDV = phienAmTVDV;
    }

    public String getNghiaTVDV() {
        return NghiaTVDV;
    }

    public void setNghiaTVDV(String nghiaTVDV) {
        NghiaTVDV = nghiaTVDV;
    }

    public String getLinkAnhtvCDDv() {
        return LinkAnhtvCDDV;
    }

    public void setLinkAnhtvCDDv(String linkAnhtvCDDV) {
        LinkAnhtvCDDV = linkAnhtvCDDV;
    }
}
