package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FasilitasDB extends JpaRepository<FasilitasModel, Long> {
    FasilitasModel findById(int id);
    RuanganModel findByRuangan(RuanganModel ruanganModel);
}
