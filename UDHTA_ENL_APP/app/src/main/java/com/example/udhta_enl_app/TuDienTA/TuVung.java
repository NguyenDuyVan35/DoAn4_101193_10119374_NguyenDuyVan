package com.example.udhta_enl_app.TuDienTA;

public class TuVung {
    private String TuVungTA;
    private String PhienAmTA;
    private String NghiaTV;
    private String LinkAnhTV;
    private String ViDu;
    private String DichVD;

    public TuVung(){

    }

    public TuVung(String tuVungTA, String phienAmTA, String nghiaTV, String linkAnhTV, String viDu, String dichVD) {
        TuVungTA = tuVungTA;
        PhienAmTA = phienAmTA;
        NghiaTV = nghiaTV;
        LinkAnhTV = linkAnhTV;
        ViDu = viDu;
        DichVD = dichVD;
    }

    public String getTuVungTA() {
        return TuVungTA;
    }

    public void setTuVungTA(String tuVungTA) {
        TuVungTA = tuVungTA;
    }

    public String getPhienAmTA() {
        return PhienAmTA;
    }

    public void setPhienAmTA(String phienAmTA) {
        PhienAmTA = phienAmTA;
    }

    public String getNghiaTV() {
        return NghiaTV;
    }

    public void setNghiaTV(String nghiaTV) {
        NghiaTV = nghiaTV;
    }

    public String getLinkAnhTV() {
        return LinkAnhTV;
    }

    public void setLinkAnhTV(String linkAnhTV) {
        LinkAnhTV = linkAnhTV;
    }

    public String getViDu() {
        return ViDu;
    }

    public void setViDu(String viDu) {
        ViDu = viDu;
    }

    public String getDichVD() {
        return DichVD;
    }

    public void setDichVD(String dichVD) {
        DichVD = dichVD;
    }
}
