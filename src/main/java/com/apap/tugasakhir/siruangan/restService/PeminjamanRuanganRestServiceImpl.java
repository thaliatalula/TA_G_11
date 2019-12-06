package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.repository.PeminjamanRuanganDB;
import com.apap.tugasakhir.siruangan.rest.Setting;
import com.apap.tugasakhir.siruangan.rest.SuratDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PeminjamanRuanganRestServiceImpl implements PeminjamanRuanganRestService {
    @Autowired
    private PeminjamanRuanganDB peminjamanRuanganDB;

    private final WebClient webClient;

    public PeminjamanRuanganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(Setting.SiTU).build();
    }

    @Override
    public PeminjamanRuanganModel createPeminjaman(PeminjamanRuanganModel peminjamanRuanganModel) {
        return peminjamanRuanganDB.save(peminjamanRuanganModel);
    }

    @Override
    public Mono<SuratDetail> getStatusSurat(String nomorSurat) {
        return this.webClient.get().uri("/api/v1/pengajuanSurat/"+nomorSurat).retrieve().bodyToMono(SuratDetail.class);
    }
}
