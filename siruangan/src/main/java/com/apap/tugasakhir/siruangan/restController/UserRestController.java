package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.rest.SiswaDetail;
import com.apap.tugasakhir.siruangan.restService.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    UserRestService userRestService;

    @GetMapping("/")
    public Mono<SiswaDetail> x(){
        return userRestService.addSiswa();
    }
}
