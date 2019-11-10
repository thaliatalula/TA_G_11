package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.GuruDetail;
import com.apap.tugasakhir.siruangan.rest.SiswaDetail;
import reactor.core.publisher.Mono;

public interface UserRestService {
    Mono<GuruDetail> addGuru(UserModel user);
    Mono<SiswaDetail> addSiswa(UserModel user);
}
