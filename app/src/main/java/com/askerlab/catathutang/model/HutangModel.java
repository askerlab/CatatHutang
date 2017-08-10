package com.askerlab.catathutang.model;

/**
 * Created by root on 01/06/17.
 */

public class HutangModel {
    private Integer id;
    private String nama;
    private String status;
    private String catatan;
    private String nominal;
    private String tanggal_hutang;
    private String tanggal_kembali;
    private String date_input;

//    public HutangModel(String nama, String status, String nominal, String tanggal_kembali) {
//        this.nama = nama;
//        this.status = status;
//        this.nominal = nominal;
//        this.tanggal_kembali = tanggal_kembali;
//    }


    public HutangModel(Integer id, String nama, String status, String catatan, String nominal, String tanggal_hutang, String tanggal_kembali, String date_input) {
        this.id = id;
        this.nama = nama;
        this.status = status;
        this.catatan = catatan;
        this.nominal = nominal;
        this.tanggal_hutang = tanggal_hutang;
        this.tanggal_kembali = tanggal_kembali;
        this.date_input = date_input;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTanggal_hutang() {
        return tanggal_hutang;
    }

    public void setTanggal_hutang(String tanggal_hutang) {
        this.tanggal_hutang = tanggal_hutang;
    }

    public String getDate_input() {
        return date_input;
    }

    public void setDate_input(String date_input) {
        this.date_input = date_input;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
