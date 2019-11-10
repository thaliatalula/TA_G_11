package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.GuruDetail;
import com.apap.tugasakhir.siruangan.rest.Setting;
import com.apap.tugasakhir.siruangan.rest.SiswaDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserRestServiceImpl implements  UserRestService{
    private final WebClient webClient;
    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(Setting.sisivitas).build();
    }

    @Override
    public Mono<GuruDetail> addGuru(UserModel user) {
        MultiValueMap<String, String > data= new LinkedMultiValueMap<>();
        data.add("idUser", "21c19e51e38f47b9930f9c6a3cee2a30");
        data.add("nig", "S07042001AB12321c19e51e38f47b9930f9c6a3cee2a30");
        data.add("nama","Ratu Mustika");
        data.add("tempatLahir", "Bandung");
        data.add("tanggalLahir", "2001-04-07");
        data.add("alamat","Jl. Lumba-Lumba");
        data.add("telepon","08526229304");
        System.out.println("aasdsad");
        return  this.webClient.post().uri("/api/students")
                .syncBody(data)
                .retrieve()
                .bodyToMono(GuruDetail.class);
    }

    @Override
    public Mono<SiswaDetail> addSiswa(UserModel user) {
        MultiValueMap<String, String > data= new LinkedMultiValueMap<>();
        data.add("idUser", "21c19e51e38f47b9930f9c6a3cee2a30");
        data.add("nis", "S07042001AB12321c19e51e38f47b9930f9c6a3cee2a30");
        data.add("nama","Ratu Mustika");
        data.add("tempatLahir", "Bandung");
        data.add("tanggalLahir", "2001-04-07");
        data.add("alamat","Jl. Lumba-Lumba");
        data.add("telepon","08526229304");
        System.out.println("xx");
        return  this.webClient.post().uri("/api/students")
                .syncBody(data)
                .retrieve()
                .bodyToMono(SiswaDetail.class);
    }
}
