package com.apap.tugasakhir.siruangan.service;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.repository.UserDB;
import com.apap.tugasakhir.siruangan.rest.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserRestServiceImpl implements UserRestService{
    @Autowired
    private UserDB userDB;

    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(Setting.sisivitas).build();
    }

    @Override
    public Mono<GuruDetailResp> addGuru(UserModel user, String NIG, String nama, String tempatLahir, Date tanggalLahir, String alamat,  String telepon) {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nig", NIG);
        data.put("nama",nama);
        data.put("tempatLahir", tempatLahir);
        data.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(tanggalLahir));
        data.put("alamat",alamat);
        data.put("telepon",telepon);
        System.out.println(data);
        return  this.webClient.post().uri("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(GuruDetailResp.class);
    }

    @Override
    public Mono<SiswaDetailResp> addSiswa(UserModel user, String NIS, String nama, String tempatLahir, Date tanggalLahir, String alamat,  String telepon) {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nis", NIS);
        data.put("nama",nama);
        data.put("tempatLahir", tempatLahir);
        data.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(tanggalLahir));
        data.put("alamat",alamat);
        data.put("telepon",telepon);
        System.out.println(data);
        return  this.webClient.post().uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(SiswaDetailResp.class);
    }
}

