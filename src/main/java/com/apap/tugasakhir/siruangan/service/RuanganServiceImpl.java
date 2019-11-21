package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.repository.RuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RuanganServiceImpl implements RuanganService {

    @Autowired
    RuanganDB ruanganDB;

    @Override
    public List<RuanganModel> getRuanganList() {
        return ruanganDB.findAll();
    }

    @Override
    public RuanganModel getRuanganById(int id) {
        return ruanganDB.findRuanganById(id);
    }

    @Override
    public List<FasilitasModel> getFasilitas(RuanganModel ruanganModel) {
        return ruanganModel.getFasilitasList();
    }

    @Override
    public void updateRuangan(RuanganModel ruanganModel) {
        RuanganModel temp=ruanganDB.findRuanganById(ruanganModel.getId());
        temp.setFasilitasList(ruanganModel.getFasilitasList());
        ruanganDB.save(temp);
    }

    @Override
    public boolean canAddFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel) {
        int counter=0;
        for (FasilitasModel fasilitas:
             ruanganModel.getFasilitasList()) {
            counter+=fasilitas.getJumlah();
        }
        if(counter+fasilitasModel.getJumlah()> ruanganModel.getKapasitas()){
            System.out.println(counter);
            return false;
        }
        return true;
    }

    @Override
    public FasilitasModel sameFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel) {
        for (FasilitasModel fasilitas: ruanganModel.getFasilitasList()
             ) {
            if(fasilitas.getNama().equalsIgnoreCase(fasilitasModel.getNama())){
                return fasilitas;
            }
        }
        return null;
    }
}
