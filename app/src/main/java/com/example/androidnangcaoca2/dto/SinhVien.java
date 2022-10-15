package com.example.androidnangcaoca2.dto;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String mssv;
    private String ten;
    private double dtb;

    public SinhVien(String mssv, String ten, double dtb) {
        this.mssv = mssv;
        this.ten = ten;
        this.dtb = dtb;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getDtb() {
        return dtb;
    }

    public void setDtb(double dtb) {
        this.dtb = dtb;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "mssv='" + mssv + '\'' +
                ", ten='" + ten + '\'' +
                ", dtb=" + dtb +
                '}';
    }
}
