package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.rest.StatusDetail;
import com.apap.tugasakhir.siruangan.restService.RuanganRestService;
import com.apap.tugasakhir.siruangan.restService.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;
    @Autowired
    private RuanganRestService ruanganRestService;
    @GetMapping("/")
    public Mono<StatusDetail> x(){
        return ruanganRestService.getStatus("2");
    }
}
