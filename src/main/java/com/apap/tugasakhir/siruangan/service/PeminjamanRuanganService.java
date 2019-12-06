package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;

import java.text.ParseException;
import java.util.List;

public interface PeminjamanRuanganService {
    List<PeminjamanRuanganModel> getPeminjamanRuanganList();
    PeminjamanRuanganModel addPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel);
    boolean canPinjamWaktu(PeminjamanRuanganModel peminjamanRuanganModel) throws ParseException;
    boolean canPinjamKapasitas(PeminjamanRuanganModel peminjamanRuanganModel);
}
