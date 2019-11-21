package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FasilitasDB extends JpaRepository<FasilitasModel, Long> {
    Optional<FasilitasModel> findById(int id);
    RuanganModel findByRuangan(RuanganModel ruanganModel);

}
