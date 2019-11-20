package com.apap.tugasakhir.siruangan.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fasilitas")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "fasilitas_ruangan",
            joinColumns = @JoinColumn(name = "ruangan_id"),
            inverseJoinColumns = @JoinColumn(name = "fasilitas_id"))
    List<RuanganModel> ruanganList;

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

    public List<RuanganModel> getRuanganList() {
        return ruanganList;
    }

    public void setRuanganList(List<RuanganModel> ruanganList) {
        this.ruanganList = ruanganList;
    }
}
