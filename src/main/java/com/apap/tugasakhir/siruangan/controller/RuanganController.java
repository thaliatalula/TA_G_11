package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.service.FasilitasService;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RuanganController {
    @Qualifier("ruanganServiceImpl")

    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private FasilitasService fasilitasService;

    @RequestMapping(value = "/ruangan/{id}", method = RequestMethod.GET)
    public String viewRuangan(@PathVariable int id, Model model){
        RuanganModel ruangan = ruanganService.getRuanganById(id);
        List<FasilitasModel> listFasilitas = ruangan.getFasilitasList();
        model.addAttribute("ruangan", ruangan);
        model.addAttribute("listFasilitas", listFasilitas);
        return "view-ruangan";
    }

    @RequestMapping(value = "/ruangan", method = RequestMethod.GET)
    public String allRuangan(Model model){
        List<RuanganModel> listRuangan= ruanganService.getRuanganList();
        model.addAttribute("listRuangan", listRuangan);
        return "all-ruangan";
    }
}
