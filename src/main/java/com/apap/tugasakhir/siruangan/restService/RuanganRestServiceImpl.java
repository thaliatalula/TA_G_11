package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.rest.Setting;
import com.apap.tugasakhir.siruangan.rest.StatusDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RuanganRestServiceImpl implements RuanganRestService {
    private final WebClient webClient;
    public RuanganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(Setting.SiTU).build();
    }
    @Override
    public Mono<StatusDetail> getStatus(String nomor) {
        return this.webClient.get().uri("/status/2").retrieve().bodyToMono(StatusDetail.class);
    }
}
