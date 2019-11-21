package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ruangan")
public class RuanganRestController {
    @Autowired
    private RuanganService ruanganService;

    @GetMapping("/")
    private List<RuanganModel> getAllRuangan(){
        return ruanganService.getRuanganList();
    }
}
