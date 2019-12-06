package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
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

    /*@Override
    public boolean canChangeFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel) {
       int counter=0;
       for (FasilitasModel fasilitasModel1:
                ruanganModel.getFasilitasList()){
           counter+=fasilitasModel1.getJumlah();
       }
       if (counter+fasilitasModel.getJumlah() > ruanganModel.getKapasitas()){
           System.out.println(counter);
           return false;
       }
       return true;
    }*/

    /*@Override
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
    }*/


}
