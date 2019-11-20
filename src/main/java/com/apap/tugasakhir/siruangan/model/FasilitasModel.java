package com.apap.tugasakhir.siruangan.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fasilitas")
@JsonIgnoreProperties(value = {"jumlah","ruanganFasilitas"}, allowSetters = true)

public class FasilitasModel  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    @OneToMany(mappedBy = "fasilitas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<RuanganFasilitasModel> ruanganFasilitas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public List<RuanganFasilitasModel> getRuanganFasilitas() {
        return ruanganFasilitas;
    }

    public void setRuanganFasilitas(List<RuanganFasilitasModel> ruanganFasilitas) {
        this.ruanganFasilitas = ruanganFasilitas;
    }
}
