package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;

import java.util.List;
import java.util.Optional;

public interface PengadaanFasilitasService {
    List<PengadaanFasilitasModel> getPengadaanFasilitasList();
    List<PengadaanFasilitasModel> findAllPengajuanByIdUser(String idUser);

}
