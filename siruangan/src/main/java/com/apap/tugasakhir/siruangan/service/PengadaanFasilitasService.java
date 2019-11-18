package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;

import java.util.List;

public interface PengadaanFasilitasService {
    List<PengadaanFasilitasModel> getPengadaanFasilitasList();
    List<PengadaanFasilitasModel> findAllPengajuanByIdUser(String idUser);
    void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas);

}
