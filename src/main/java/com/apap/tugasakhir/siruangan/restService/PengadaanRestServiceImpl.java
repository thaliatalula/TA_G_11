package com.apap.tugasakhir.siruangan.restService;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.*;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
public class PengadaanRestServiceImpl implements PengadaanRestService {
    private final WebClient webClient;


    public PengadaanRestServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl(Setting.SiPerpus).build();
    }

    @Override
    public Mono<BukuDetailResp> addBuku(BukuDetail buku, UserModel user) {

        JSONObject data = new JSONObject();
        data.put("judul",buku.getJudul());
        data.put("pengarang",buku.getPengarang());
        data.put("penerbit",buku.getPenerbit());
        data.put("jumlah",buku.getJumlah());
        data.put("harga",buku.getHarga());
        data.put("userId", user.getUuid());
        System.out.println(data);
        return this.webClient.post()
                .uri("/api/v1/pengadaan/tambah")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data.toString())
                .retrieve()
                .bodyToMono(BukuDetailResp.class);
    }
}
