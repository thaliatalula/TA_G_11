package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.rest.StatusDetail;
import reactor.core.publisher.Mono;

public interface RuanganRestService {
    Mono<StatusDetail> getStatus(String nomor);
}
