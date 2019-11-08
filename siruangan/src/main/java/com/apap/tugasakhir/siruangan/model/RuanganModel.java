package com.apap.tugasakhir.siruangan.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ruangan")
public class RuanganModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "kapasitas", nullable = false)
    private int kapasitas;

    @ManyToMany(mappedBy = "ruanganList", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<FasilitasModel> fasilitasList;

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
