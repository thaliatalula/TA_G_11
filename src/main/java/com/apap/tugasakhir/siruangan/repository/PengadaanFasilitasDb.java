package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PengadaanFasilitasDb extends JpaRepository<PengadaanFasilitasModel, Long> {
    List<PengadaanFasilitasModel> findByUserUuid(String uuidUser);
}
