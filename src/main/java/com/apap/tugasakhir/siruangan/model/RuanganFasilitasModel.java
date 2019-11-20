package com.apap.tugasakhir.siruangan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "ruanganFasilitas")
@JsonIgnoreProperties(value = {"idRuangan", "id"}, allowSetters = true)
public class RuanganFasilitasModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idRuangan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RuanganModel ruangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idFasilitas", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FasilitasModel fasilitas;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private int jumlah;

    public RuanganModel getRuangan() {
        return ruangan;
    }

    public void setRuangan(RuanganModel ruangan) {
        this.ruangan = ruangan;
    }

    public FasilitasModel getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(FasilitasModel fasilitas) {
        this.fasilitas = fasilitas;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
