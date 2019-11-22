package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.restService.FasilitasRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fasilitas")
public class FasilitasRestController {
    @Autowired
    private FasilitasRestService fasilitasRestService;

    @GetMapping(value = "/koperasi")
    private List<FasilitasModel> retrieveListFasilitas() {
        return fasilitasRestService.retrieveListFasilitas();
    }
}

