package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;

import java.util.List;

public interface FasilitasService {
    List<FasilitasModel> getFasilitasList();
    FasilitasModel getFasilitasById(int id);
}
