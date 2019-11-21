package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.List;

public interface RuanganService {
    List<RuanganModel> getRuanganList();
    RuanganModel getRuanganById(int id);
    List<FasilitasModel> getFasilitas(RuanganModel ruanganModel);
    void updateRuangan(RuanganModel ruanganModel);
    boolean canAddFasilitas(RuanganModel ruanganModel,FasilitasModel fasilitasModel);
    FasilitasModel sameFasilitas(RuanganModel ruanganModel, FasilitasModel fasilitasModel);
}
