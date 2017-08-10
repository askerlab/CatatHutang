package com.askerlab.catathutang.helper;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 01/06/17.
 */

public class Hutang extends RealmObject {
    @PrimaryKey
    private int id;
    private String nama;
    private String nominal;
    private String tanggal_hutang;
    private String tanggal_kembali;
    private String catatan;
    private String status;
    private String date_input;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getTanggal_hutang() {
        return tanggal_hutang;
    }

    public void setTanggal_hutang(String tanggal_hutang) {
        this.tanggal_hutang = tanggal_hutang;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_input() {
        return date_input;
    }

    public void setDate_input(String date_input) {
        this.date_input = date_input;
    }
}
