package com.apap.tugasakhir.siruangan.service;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class PeminjamanRuanganServiceImpl implements PeminjamanRuanganService {
    @Autowired
    private PeminjamanRuanganDB peminjamanRuanganDB;

    public long getTimeFromFormat(Date date, String time) throws ParseException {
        SimpleDateFormat formatDate= new SimpleDateFormat("yyyy-MM-dd");
        String dateTime= formatDate.format(date);
        dateTime=dateTime.concat(" "+time);
        SimpleDateFormat formatAll=  new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatAll.parse(dateTime).getTime();
    }
    @Override
    public void updateStatus(PeminjamanRuanganModel peminjamanRuanganModel) {
        PeminjamanRuanganModel peminjaman=peminjamanRuanganDB.findById(peminjamanRuanganModel.getId()).get();
        peminjaman.setDisetujui(peminjamanRuanganModel.isDisetujui());
        peminjamanRuanganDB.save(peminjaman);

    }

    public List<PeminjamanRuanganModel> getPeminjamanRuanganList(){
        return peminjamanRuanganDB.findAll();
    }

    @Override
    public PeminjamanRuanganModel getPeminjamanByIdPeminjaman(Integer idPeminjaman){
        return peminjamanRuanganDB.findById(idPeminjaman).get();
    }


    @Override
    public PeminjamanRuanganModel addPeminjamanRuangan(PeminjamanRuanganModel peminjamanRuanganModel) {
        return peminjamanRuanganDB.save(peminjamanRuanganModel);
    }

    @Override
    public boolean canPinjamWaktu(PeminjamanRuanganModel peminjamanRuanganModel) throws ParseException {
        RuanganModel ruangan= peminjamanRuanganModel.getRuangan();
        for (PeminjamanRuanganModel peminjaman: ruangan.getPeminjamanRuanganList()
             ) {

            if( getTimeFromFormat(peminjamanRuanganModel.getTanggalMulai(),peminjamanRuanganModel.getWaktuMulai())<=getTimeFromFormat(peminjaman.getTanggalSelesai(),peminjaman.getWaktuSelesai())
                    && getTimeFromFormat(peminjamanRuanganModel.getTanggalSelesai(),peminjamanRuanganModel.getWaktuSelesai())>=getTimeFromFormat(peminjaman.getTanggalMulai(),peminjaman.getWaktuMulai()) ){
                   return false;
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
