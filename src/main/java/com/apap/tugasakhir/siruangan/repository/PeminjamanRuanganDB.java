package com.apap.tugasakhir.siruangan.repository;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PeminjamanRuanganDB extends JpaRepository<PeminjamanRuanganModel, Long> {
    PeminjamanRuanganModel findById(Integer id);
    Optional<PeminjamanRuanganModel> findByRuangan(RuanganModel ruanganModel);
}
