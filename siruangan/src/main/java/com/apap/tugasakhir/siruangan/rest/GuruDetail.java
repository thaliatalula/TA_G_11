package com.apap.tugasakhir.siruangan.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties
public class GuruDetail {
    @JsonProperty("idUser")
    private String uuid_user;
    @JsonProperty("nig")
    private String nig;
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("tempatLahir")
    private String tempat_lahir;
    @JsonProperty("tanggalLahir")
    private Date tanggal_lahir;
    @JsonProperty("alamat")
    private String alamat;
    @JsonProperty("telepon")
    private String telepon;

    public String getUuid_user() {
        return uuid_user;
    }

    public void setUuid_user(String uuid_user) {
        this.uuid_user = uuid_user;
    }

    public String getNig() {
        return nig;
    }

    public void setNig(String nig) {
        this.nig = nig;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Date getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(Date tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
