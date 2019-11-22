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
    public Mono<UsersDetailResp> addGuru(UserModel user, UsersDetail guru) {
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
                .bodyToMono(UsersDetailResp.class);
    }
    @Override
    public Mono<UsersDetailResp> addAdmin(UserModel user, UsersDetail admin) {
        JSONObject data= new JSONObject();
        data.put("idUser", user.getUuid());
        data.put("nip", admin.getNip());
        data.put("nama",admin.getNama());
        data.put("tempatLahir", admin.getTempatLahir());
        data.put("tanggalLahir", new SimpleDateFormat("yyyy-mm-dd").format(admin.getTanggalLahir()));
        data.put("alamat",admin.getAlamat());
        data.put("telepon",admin.getTelepon());
        System.out.println(data);
        return  this.webClient.post().uri("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(UsersDetailResp.class);
    }

    @Override
    public Mono<UsersDetailResp> addSiswa(UserModel user, UsersDetail siswa) {
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
                .bodyToMono(UsersDetailResp.class);
    }

    @Override
    public Mono<UsersDetailResp> getGuru(String uuid) {
        return this.webClient.get().uri("/api/teachers/" + uuid).retrieve().bodyToMono(UsersDetailResp.class);
    }
    @Override
    public Mono<UsersDetailResp> getSiswa(String uuid) {
        return this.webClient.get().uri("/api/students/"+uuid).retrieve().bodyToMono(UsersDetailResp.class);
    }
}

