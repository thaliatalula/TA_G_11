package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PengadaanFasilitasDb extends JpaRepository<PengadaanFasilitasModel, Long> {
    List<PengadaanFasilitasModel> findByUserId(String idUser);
}
