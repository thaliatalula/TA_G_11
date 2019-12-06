package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.SuratDetail;
import reactor.core.publisher.Mono;

public interface PeminjamanRuanganRestService {
    PeminjamanRuanganModel createPeminjaman(PeminjamanRuanganModel peminjamanRuanganModel);
    Mono<SuratDetail> getStatusSurat(String nomorSurat);
}
