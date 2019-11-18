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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FasilitasController {
    @Autowired
    private RuanganService ruanganService;

    @Autowired
    private FasilitasService fasilitasService;

    @RequestMapping(value = "/tambah-fasilitas", method = RequestMethod.GET)
    private String addFasilitas(Model model){
        List<RuanganModel> listRuangan=  ruanganService.getRuanganList();
        model.addAttribute("listRuangan",listRuangan);
        return "tambah-fasilitas";
    }
    @RequestMapping(value = "/tambah-fasilitas", method = RequestMethod.POST)
    private String addFasilitasSubmit(@ModelAttribute FasilitasModel fasilitas, HttpServletRequest request){
        RuanganModel ruangan =ruanganService.getRuanganById(Integer.parseInt(request.getParameter("ruangan")));
        return "redirect:/tambah-fasilitas";
    }


}
