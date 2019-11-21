package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.repository.RuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FasilitasRestServiceimpl implements FasilitasRestService {
    @Autowired
    private RuanganDB ruanganDB;

    @Override
    public List<FasilitasModel> retrieveListFasilitas() {
        RuanganModel ruangan = ruanganDB.findRuanganByNama("Koperasi");
        List<FasilitasModel> listFasilitas = ruangan.getFasilitasList();
        return listFasilitas;
    }
}

