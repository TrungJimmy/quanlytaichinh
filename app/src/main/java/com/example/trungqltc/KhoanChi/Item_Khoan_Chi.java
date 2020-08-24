package com.example.trungqltc.KhoanChi;

public class Item_Khoan_Chi {

    int id_2;
    String danhmuc_2;
    float tien_2;
    String ngay_2;
    String ghichu_2;


    public Item_Khoan_Chi() {
    }

    public Item_Khoan_Chi(int tien_2) {
        this.tien_2 = tien_2;
    }

    public Item_Khoan_Chi(String danhmuc_2, float tien_2, String ngay_2, String ghichu_2) {
        this.danhmuc_2 = danhmuc_2;
        this.tien_2 = tien_2;
        this.ngay_2 = ngay_2;
        this.ghichu_2 = ghichu_2;
    }

    public Item_Khoan_Chi(int id_2, String danhmuc_2, float tien_2, String ngay_2, String ghichu_2) {
        this.id_2 = id_2;
        this.danhmuc_2 = danhmuc_2;
        this.tien_2 = tien_2;
        this.ngay_2 = ngay_2;
        this.ghichu_2 = ghichu_2;
    }

    public int getId_2() {
        return id_2;
    }

    public void setId_2(int id_2) {
        this.id_2 = id_2;
    }

    public String getDanhmuc_2() {
        return danhmuc_2;
    }

    public void setDanhmuc_2(String danhmuc_2) {
        this.danhmuc_2 = danhmuc_2;
    }

    public float getTien_2() {
        return tien_2;
    }

    public void setTien_2(float tien_2) {
        this.tien_2 = tien_2;
    }

    public String getNgay_2() {
        return ngay_2;
    }

    public void setNgay_2(String ngay_2) {
        this.ngay_2 = ngay_2;
    }

    public String getGhichu_2() {
        return ghichu_2;
    }

    public void setGhichu_2(String ghichu_2) {
        this.ghichu_2 = ghichu_2;
    }
}