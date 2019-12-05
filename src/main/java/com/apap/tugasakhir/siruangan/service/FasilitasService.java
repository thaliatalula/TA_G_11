package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.List;

public interface FasilitasService {
    List<FasilitasModel> getFasilitasList();
    FasilitasModel getFasilitasById(int id);
    void addFasilitas(FasilitasModel fasilitasModel);
    void  deleteFasilitas(FasilitasModel fasilitas);
    FasilitasModel ubahJumlahFasilitas(FasilitasModel fasilitas);
    /*boolean canChangeFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel);*/
}
