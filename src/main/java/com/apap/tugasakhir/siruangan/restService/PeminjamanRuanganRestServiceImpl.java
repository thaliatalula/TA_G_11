package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PeminjamanRuanganRestServiceImpl implements PeminjamanRuanganRestService {
    @Autowired
    private PeminjamanRuanganDB peminjamanRuanganDB;

    @Override
    public PeminjamanRuanganModel createPeminjaman(PeminjamanRuanganModel peminjamanRuanganModel) {
        return peminjamanRuanganDB.save(peminjamanRuanganModel);
    }
}
