package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.*;
import org.springframework.security.core.userdetails.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface UserRestService{
    Mono<UsersDetailResp> addGuru(UserModel user, UsersDetail guru);
    Mono<UsersDetailResp> addSiswa(UserModel user, UsersDetail siswa);
    Mono<UsersDetailResp> addAdmin(UserModel user, UsersDetail admin);
    Mono<UsersDetailResp> getGuru(String uuid);
    Mono<UsersDetailResp> getSiswa(String uuid);


}
