package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.rest.SiswaDetail;
import com.apap.tugasakhir.siruangan.rest.SiswaDetailResp;
import com.apap.tugasakhir.siruangan.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    private UserRestService userRestService;



//    @GetMapping("/")
//    public Mono<SiswaDetailResp> x(){
//        return userRestService.addSiswa();
//    }
}
