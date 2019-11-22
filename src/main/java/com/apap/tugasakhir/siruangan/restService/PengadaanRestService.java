package com.apap.tugasakhir.siruangan.restService;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.BukuDetail;
import com.apap.tugasakhir.siruangan.rest.BukuDetailResp;
import reactor.core.publisher.Mono;

public interface PengadaanRestService {
    Mono<BukuDetailResp> addBuku(BukuDetail buku, UserModel userModel);
}
