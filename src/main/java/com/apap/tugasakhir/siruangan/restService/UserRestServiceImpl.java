package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.repository.UserDB;
import com.apap.tugasakhir.siruangan.rest.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;

@Service
public class UserRestServiceImpl implements UserRestService{
    @Autowired
    private UserDB userDB;

    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(Setting.sisivitas).build();
    }

    @Override
    public Mono<GuruDetailResp> addGuru(UserModel user, GuruDetail guru) {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nig", guru.getNig());
        data.put("nama",guru.getNama());
        data.put("tempatLahir", guru.getTempatLahir());
        data.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(guru.getTanggalLahir()));
        data.put("alamat",guru.getAlamat());
        data.put("telepon",guru.getTelepon());
        System.out.println(data);
        return  this.webClient.post().uri("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(GuruDetailResp.class);
    }

    @Override
    public Mono<SiswaDetailResp> addSiswa(UserModel user, SiswaDetail siswa) {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nis", siswa.getNis());
        data.put("nama",siswa.getNama());
        data.put("tempatLahir", siswa.getTempatLahir());
        data.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(siswa.getTanggalLahir()));
        data.put("alamat",siswa.getAlamat());
        data.put("telepon",siswa.getTelepon());
        System.out.println(data);
        return  this.webClient.post().uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(SiswaDetailResp.class);
    }

    @Override
    public Mono<GuruDetailResp> getGuru(String uuid) {
        return this.webClient.get().uri("/api/teachers/" + uuid).retrieve().bodyToMono(GuruDetailResp.class);
    }
    @Override
    public Mono<SiswaDetailResp> getSiswa(String uuid) {
        return this.webClient.get().uri("/api/students/"+uuid).retrieve().bodyToMono(SiswaDetailResp.class);
    }
}

