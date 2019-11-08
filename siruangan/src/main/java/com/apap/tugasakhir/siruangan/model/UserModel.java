package com.apap.tugasakhir.siruangan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Lob
    @Column(name="password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PengadaanFasilitasModel> listPengadaanFasilitas;

    @OneToMany(mappedBy = "userPeminjam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PeminjamanRuanganModel> listPeminjamanRuanganPeminjam;

    @OneToMany(mappedBy = "userPenyetuju", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PeminjamanRuanganModel> listPeminjamanRuanganPenyetuju;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public List<PengadaanFasilitasModel> getListPengadaanFasilitas() {
        return listPengadaanFasilitas;
    }

    public void setListPengadaanFasilitas(List<PengadaanFasilitasModel> listPengadaanFasilitas) {
        this.listPengadaanFasilitas = listPengadaanFasilitas;
    }

    public List<PeminjamanRuanganModel> getListPeminjamanRuanganPeminjam() {
        return listPeminjamanRuanganPeminjam;
    }

    public void setListPeminjamanRuanganPeminjam(List<PeminjamanRuanganModel> listPeminjamanRuanganPeminjam) {
        this.listPeminjamanRuanganPeminjam = listPeminjamanRuanganPeminjam;
    }

    public List<PeminjamanRuanganModel> getListPeminjamanRuanganPenyetuju() {
        return listPeminjamanRuanganPenyetuju;
    }

    public void setListPeminjamanRuanganPenyetuju(List<PeminjamanRuanganModel> listPeminjamanRuanganPenyetuju) {
        this.listPeminjamanRuanganPenyetuju = listPeminjamanRuanganPenyetuju;
    }
}
