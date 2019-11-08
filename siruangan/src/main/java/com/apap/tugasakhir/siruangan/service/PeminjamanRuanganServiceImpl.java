package com.apap.tugasakhir.siruangan.service;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService {
    @Autowired
    private PeminjamanRuanganDB peminjamanRuanganDB;

    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDB.findAll();
    }

}
