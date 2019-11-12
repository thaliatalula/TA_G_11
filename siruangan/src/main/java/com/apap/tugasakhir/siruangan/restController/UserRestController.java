package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.restService.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
