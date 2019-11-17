package com.apap.tugasakhir.siruangan.repository;

import com.apap.tugasakhir.siruangan.model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuanganDB extends JpaRepository<RuanganModel, Long> {
    RuanganModel findRuanganById(int id);
}
