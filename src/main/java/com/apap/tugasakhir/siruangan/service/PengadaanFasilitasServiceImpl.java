package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import com.apap.tugasakhir.siruangan.repository.PengadaanFasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    @Override
    public void addPengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas){
        pengadaanFasilitasDb.save(pengadaanFasilitas);
    }

    @Override
    public Optional<PengadaanFasilitasModel> getPengadaanById(Integer id){
        return pengadaanFasilitasDb.findById(id);
    }

    @Override
    public void deletePengadaanFasilitas(PengadaanFasilitasModel pengadaanFasilitas){
        pengadaanFasilitasDb.delete(pengadaanFasilitas);
    }
}
