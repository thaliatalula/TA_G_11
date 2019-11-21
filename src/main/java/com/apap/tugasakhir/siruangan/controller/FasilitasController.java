package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.service.FasilitasService;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FasilitasController {
    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private FasilitasService fasilitasService;

    @RequestMapping(value = "/fasilitas/tambah", method = RequestMethod.GET)
    private String addFasilitas(Model model){
        List<RuanganModel> listRuangan=  ruanganService.getRuanganList();
        model.addAttribute("listRuangan",listRuangan);
        return "tambah-fasilitas";
    }
    @RequestMapping(value = "/fasilitas/tambah", method = RequestMethod.POST)
    private String addFasilitasSubmit(@ModelAttribute FasilitasModel fasilitas, RedirectAttributes attributes){
        RuanganModel ruanganTarget=fasilitas.getRuangan();
        if(ruanganTarget.getFasilitasList().size()==0 && ruanganTarget.getKapasitas()>fasilitas.getJumlah()){
            fasilitasService.addFasilitas(fasilitas);
            ruanganTarget.getFasilitasList().add(fasilitas);
            ruanganService.updateRuangan(ruanganTarget);
            return "redirect:/ruangan/"+ruanganTarget.getId();
        }
        if(!ruanganService.canAddFasilitas(ruanganTarget,fasilitas)){
            attributes.addFlashAttribute("gagal","Jumlah fasilitas melebihi kapasitas ruangan");
            return "redirect:/fasilitas/tambah";
        }
        if(ruanganService.sameFasilitas(ruanganTarget,fasilitas)!=null){
            FasilitasModel fasilitasSama=ruanganService.sameFasilitas(ruanganTarget,fasilitas);
            fasilitasSama.setJumlah(fasilitasSama.getJumlah()+fasilitas.getJumlah());
            ruanganService.updateRuangan(ruanganTarget);
        }
        else{
            fasilitasService.addFasilitas(fasilitas);
            ruanganTarget.getFasilitasList().add(fasilitas);
            ruanganService.updateRuangan(ruanganTarget);
        }
        return "redirect:/ruangan/"+ruanganTarget.getId();
    }


}
