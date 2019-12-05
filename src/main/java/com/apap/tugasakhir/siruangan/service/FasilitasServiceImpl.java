package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.repository.FasilitasDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FasilitasServiceImpl implements FasilitasService{

    @Autowired
    FasilitasDB fasilitasDB;


    @Override
    public List<FasilitasModel> getFasilitasList() {
        return fasilitasDB.findAll();
    }

    @Override
    public FasilitasModel getFasilitasById(int id) {
        return fasilitasDB.findById(id);
    }


    @Override
    public void addFasilitas(FasilitasModel fasilitasModel) {
        fasilitasDB.save(fasilitasModel);
    }

    @Override
    public void deleteFasilitas(FasilitasModel fasilitas) {
        fasilitasDB.delete(fasilitas);
    }

    @Override
    public FasilitasModel ubahJumlahFasilitas(FasilitasModel fasilitas) {
        FasilitasModel targetFasilitas = fasilitasDB.findById(fasilitas.getId());
        targetFasilitas.setJumlah(fasilitas.getJumlah());
        fasilitasDB.save(targetFasilitas);
        return targetFasilitas;
    }

}
