package com.apap.tugasakhir.siruangan.model;


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
    private int kapasitas;

    @OneToMany(mappedBy = "ruangan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public int getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public List<RuanganFasilitasModel> getRuanganFasilitas() {
        return ruanganFasilitas;
    }

    public void setRuanganFasilitas(List<RuanganFasilitasModel> ruanganFasilitas) {
        this.ruanganFasilitas = ruanganFasilitas;
    }
}
