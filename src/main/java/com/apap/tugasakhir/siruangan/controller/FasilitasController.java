package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.service.FasilitasService;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        if(ruanganTarget.getFasilitasList().size()==0){
            fasilitasService.addFasilitas(fasilitas);
            ruanganTarget.getFasilitasList().add(fasilitas);
            ruanganService.updateRuangan(ruanganTarget);
            return "redirect:/ruangan/"+ruanganTarget.getId();
        }
//        if(!ruanganService.canAddFasilitas(ruanganTarget,fasilitas)){
//            attributes.addFlashAttribute("gagal","Jumlah fasilitas melebihi kapasitas ruangan");
//            return "redirect:/fasilitas/tambah";
//        }
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

    @RequestMapping(value = "fasilitas/ubah/{id}", method = RequestMethod.GET)
    public String ubahFasilitas(@PathVariable(value = "id") int id, Model model){
        FasilitasModel existingFasilitas = fasilitasService.getFasilitasById(id);
        model.addAttribute("fasilitas", existingFasilitas);
        model.addAttribute("ruangan", existingFasilitas.getRuangan());
        return "form-ubah-fasilitas";
    }

    @RequestMapping(value = "/fasilitas/ubah/{id}", method = RequestMethod.POST)
    public String ubahFasilitasSubmit(@PathVariable(value = "id") int id, @ModelAttribute FasilitasModel fasilitas, Model model){
        FasilitasModel newFasilitas = fasilitasService.ubahJumlahFasilitas(fasilitas);
        RuanganModel ruanganTarget=newFasilitas.getRuangan();
        model.addAttribute("fasilitas", newFasilitas);
        return "redirect:/ruangan/"+ruanganTarget.getId();
        /*return "ubah-fasilitas";*/
    }

    @RequestMapping("/fasilitas/delete/{id}")
    public String deleteFasilitas(@PathVariable(value = "id") int id, @ModelAttribute FasilitasModel fasilitas, Model model){
        FasilitasModel fasilitasDel = fasilitasService.getFasilitasById(id);
        RuanganModel ruanganTarget=fasilitasDel.getRuangan();
        model.addAttribute("ruangan", fasilitasDel.getRuangan());
        fasilitasService.deleteFasilitas(fasilitasDel);
        model.addAttribute("fasilitas", fasilitasDel);
        return "redirect:/ruangan/"+ruanganTarget.getId();
    }





}
