package com.example.trungqltc.DoanhThu;

public class ItemDoanhThu {
    int id;
    String danhmuc_1;
    int tien_1;
    String ngay_1;
    String ghichu_1;

    public ItemDoanhThu() {
    }

    public ItemDoanhThu(int tien_1) {
        this.tien_1 = tien_1;
    }


    public ItemDoanhThu(int id, String danhmuc_1, int tien_1, String ngay_1, String ghichu_1) {
        this.id = id;
        this.danhmuc_1 = danhmuc_1;
        this.tien_1 = tien_1;
        this.ngay_1 = ngay_1;
        this.ghichu_1 = ghichu_1;
    }

    public ItemDoanhThu(String danhmuc_1, int tien_1, String ngay_1, String ghichu_1) {
        this.danhmuc_1 = danhmuc_1;
        this.tien_1 = tien_1;
        this.ngay_1 = ngay_1;
        this.ghichu_1 = ghichu_1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDanhmuc_1() {
        return danhmuc_1;
    }

    public void setDanhmuc_1(String danhmuc_1) {
        this.danhmuc_1 = danhmuc_1;
    }

    public int getTien_1() {
        return tien_1;
    }

    public void setTien_1(int tien_1) {
        this.tien_1 = tien_1;
    }

    public String getNgay_1() {
        return ngay_1;
    }

    public void setNgay_1(String ngay_1) {
        this.ngay_1 = ngay_1;
    }

    public String getGhichu_1() {
        return ghichu_1;
    }

    public void setGhichu_1(String ghichu_1) {
        this.ghichu_1 = ghichu_1;
    }
}

