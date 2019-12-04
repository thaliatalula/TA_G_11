package com.apap.tugasakhir.siruangan.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ruangan")
public class RuanganModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    @JsonIgnore
    private int kapasitas;

    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<FasilitasModel> fasilitasList;

    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PeminjamanRuanganModel> peminjamanRuanganList;


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

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public List<FasilitasModel> getFasilitasList() {
        return fasilitasList;
    }

    public void setFasilitasList(List<FasilitasModel> fasilitasList) {
        this.fasilitasList = fasilitasList;
    }
}
