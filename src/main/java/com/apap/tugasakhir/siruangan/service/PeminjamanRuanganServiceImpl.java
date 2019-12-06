package com.apap.tugasakhir.siruangan.service;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService {
    @Autowired
    private PeminjamanRuanganDB peminjamanRuanganDB;

    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDB.findAll();
    }

    @Override
    public PeminjamanRuanganModel addPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel) {
        return peminjamanRuanganDB.save(peminjamanRuanganModel);
    }

    @Override
    public boolean canPinjamWaktu(PeminjamanRuanganModel peminjamanRuanganModel) throws ParseException {
        SimpleDateFormat formatWaktu= new SimpleDateFormat("HH:mm");
        RuanganModel ruangan= peminjamanRuanganModel.getRuangan();

        for (PeminjamanRuanganModel peminjaman: ruangan.getPeminjamanRuanganList()
             ) {
            if(peminjamanRuanganModel.getTanggalMulai().getTime()<=peminjaman.getTanggalSelesai().getTime() && peminjamanRuanganModel.getTanggalSelesai().getTime()>=peminjaman.getTanggalMulai().getTime() ){
                if(formatWaktu.parse(peminjamanRuanganModel.getWaktuMulai()).getTime()<=formatWaktu.parse(peminjaman.getWaktuSelesai()).getTime() && formatWaktu.parse(peminjamanRuanganModel.getWaktuSelesai()).getTime()>=formatWaktu.parse(peminjaman.getWaktuMulai()).getTime()){
                   return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canPinjamKapasitas(PeminjamanRuanganModel peminjamanRuanganModel) {
        RuanganModel ruangan= peminjamanRuanganModel.getRuangan();
        if(ruangan.getKapasitas()< peminjamanRuanganModel.getJumlahPeserta()){
            return false;
        }
        return true;
    }
}
