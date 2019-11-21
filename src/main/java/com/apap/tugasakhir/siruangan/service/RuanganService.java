package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;

import java.util.List;

public interface RuanganService {
    List<RuanganModel> getRuanganList();
    RuanganModel getRuanganById(int id);
    List<FasilitasModel> getFasilitas(RuanganModel ruanganModel);
}
