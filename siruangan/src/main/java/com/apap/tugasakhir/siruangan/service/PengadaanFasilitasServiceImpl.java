package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import com.apap.tugasakhir.siruangan.repository.PengadaanFasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PengadaanFasilitasServiceImpl implements PengadaanFasilitasService{
    @Autowired
    private PengadaanFasilitasDb pengadaanFasilitasDb;

    @Override
    public List<PengadaanFasilitasModel> getPengadaanFasilitasList(){
        return pengadaanFasilitasDb.findAll();
    }

    @Override
    public List<PengadaanFasilitasModel> findAllPengajuanByIdUser(String idUser){
        return pengadaanFasilitasDb.findByUserUuid(idUser);
    }
}
