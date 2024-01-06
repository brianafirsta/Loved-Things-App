package com.example.lovedthingsapp.Model;

import java.io.Serializable;

public class BawahanWanitaModel implements Serializable {
    String img_url;
    String nama;
    String ukuran;
    String harga;
    String desc;

    public BawahanWanitaModel() {
    }

    public BawahanWanitaModel(String img_url, String nama, String ukuran, String harga) {
        this.img_url = img_url;
        this.nama = nama;
        this.ukuran = ukuran;
        this.harga = harga;
        this.desc = desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
