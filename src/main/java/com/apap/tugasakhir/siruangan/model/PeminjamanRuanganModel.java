package com.apap.tugasakhir.siruangan.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "peminjaman_ruangan")
@JsonIgnoreProperties(value = {"ruangan", "userPenyetuju"}, allowSetters = true)
public class PeminjamanRuanganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "waktu_mulai", nullable = false)
    private String waktuMulai;

    @NotNull
    @Column(name = "waktu_selesai", nullable = false)
    private String waktuSelesai;

    @NotNull
    @Column(name = "tanggal_mulai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date tanggalMulai;

    @NotNull
    @Column(name = "tanggal_selesai", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date tanggalSelesai;

    @NotNull
    @Column(name = "tujuan", nullable = false)
    private String tujuan;

    @NotNull
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "jumlah_peserta", nullable = false)
    private int jumlahPeserta;

    @NotNull
    @Column(name = "is_disetujui", nullable = false)
    private boolean isDisetujui=false;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_ruangan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RuanganModel ruangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuid_user_peminjam", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel userPeminjam;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "uuid_user_penyetuju", referencedColumnName = "uuid")
    @OnDelete(action = OnDeleteAction.CASCADE )
    @JsonIgnore
    private UserModel userPenyetuju=null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getJumlahPeserta() {
        return jumlahPeserta;
    }

    public void setJumlahPeserta(int jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }

    public boolean isDisetujui() {
        return isDisetujui;
    }

    public void setDisetujui(boolean disetujui) {
        isDisetujui = disetujui;
    }

    public RuanganModel getRuangan() {
        return ruangan;
    }

    public void setRuangan(RuanganModel ruangan) {
        this.ruangan = ruangan;
    }

    public UserModel getUserPenyetuju() {
        return userPenyetuju;
    }

    public void setUserPenyetuju(UserModel userPenyetuju) {
        this.userPenyetuju = userPenyetuju;
    }

    public UserModel getUserPeminjam() {
        return userPeminjam;
    }

    public void setUserPeminjam(UserModel userPeminjam) {
        this.userPeminjam = userPeminjam;
    }
}
