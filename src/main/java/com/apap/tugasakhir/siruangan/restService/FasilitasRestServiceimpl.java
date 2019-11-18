package com.apap.tugasakhir.siruangan.restService;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.service.FasilitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FasilitasRestServiceimpl implements FasilitasRestService{
    @Autowired
    private FasilitasService fasilitasService;

    @Override
    public List<FasilitasModel> retriveListFasilitas(){
        return fasilitasService.getFasilitasList();
    }
}
