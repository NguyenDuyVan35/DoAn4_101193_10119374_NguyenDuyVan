package com.example.udhta_enl_app.HocTap.CDMonhoc;

public class TvCDMonhoc {
    String TvMonhoc;
    String PhienAmTvMonhoc;
    String NghiaTvMonhoc;
    String LinkAnhTvMonHoc;

    public TvCDMonhoc(){}

    public TvCDMonhoc(String tvMonhoc, String phienAmTvMonhoc, String nghiaTvMonhoc, String linkAnhTvMonHoc) {
        TvMonhoc = tvMonhoc;
        PhienAmTvMonhoc = phienAmTvMonhoc;
        NghiaTvMonhoc = nghiaTvMonhoc;
        LinkAnhTvMonHoc = linkAnhTvMonHoc;
    }

    public String getTvMonhoc() {
        return TvMonhoc;
    }

    public void setTvMonhoc(String tvMonhoc) {
        TvMonhoc = tvMonhoc;
    }

    public String getPhienAmTvMonhoc() {
        return PhienAmTvMonhoc;
    }

    public void setPhienAmTvMonhoc(String phienAmTvMonhoc) {
        PhienAmTvMonhoc = phienAmTvMonhoc;
    }

    public String getNghiaTvMonhoc() {
        return NghiaTvMonhoc;
    }

    public void setNghiaTvMonhoc(String nghiaTvMonhoc) {
        NghiaTvMonhoc = nghiaTvMonhoc;
    }

    public String getLinkAnhTvMonHoc() {
        return LinkAnhTvMonHoc;
    }

    public void setLinkAnhTvMonHoc(String linkAnhTvMonHoc) {
        LinkAnhTvMonHoc = linkAnhTvMonHoc;
    }
}
