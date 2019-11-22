package com.apap.tugasakhir.siruangan.restService;
import com.apap.tugasakhir.siruangan.rest.BukuDetail;
import reactor.core.publisher.Mono;

public interface PengadaanRestService {
    Mono<BukuDetailResp> addBuku(BukuDetail buku);
}
