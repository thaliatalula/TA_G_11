package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.GuruDetail;
import com.apap.tugasakhir.siruangan.rest.GuruDetailResp;
import com.apap.tugasakhir.siruangan.rest.SiswaDetail;
import com.apap.tugasakhir.siruangan.rest.SiswaDetailResp;
import org.springframework.security.core.userdetails.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface UserRestService{
    Mono<GuruDetailResp> addGuru(UserModel user, GuruDetail guru);
    Mono<SiswaDetailResp> addSiswa(UserModel user, SiswaDetail siswa);
}
