package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.PengadaanFasilitasService;
import com.apap.tugasakhir.siruangan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PengadaanFasilitasController {
    @Autowired
    private PengadaanFasilitasService pengadaanFasilitasService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pengadaan-fasilitas/daftar-pengajuan")
    public String daftarPengajuan(Model model, Authentication authentication){
        UserModel user = userService.findByUserName(authentication.getName());
        if(user.getRole().getNama().equalsIgnoreCase("guru")){
            List<PengadaanFasilitasModel> daftarPengajuanGuru = pengadaanFasilitasService.findAllPengajuanByIdUser(user.getUuid());
            model.addAttribute("daftarPengajuan", daftarPengajuanGuru);
        }
        else{
            List<PengadaanFasilitasModel> daftarPengajuan = pengadaanFasilitasService.getPengadaanFasilitasList();
            model.addAttribute("daftarPengajuan", daftarPengajuan);
        }
        return "daftar-pengajuan-fasilitas";
    }

    @RequestMapping(value = "/pengadaan-fasilitas/add", method = RequestMethod.GET)
    public String addPengadaanFasilitasForm(Model model, Authentication authentication){
        UserModel user = userService.findByUserName(authentication.getName());
        ArrayList<PengadaanFasilitasModel> listPengadaanFasilitas = new ArrayList<>();
        user.setListPengadaanFasilitas(listPengadaanFasilitas);
        PengadaanFasilitasModel newPengadaan = new PengadaanFasilitasModel();
        listPengadaanFasilitas.add(newPengadaan);

        model.addAttribute("user", user);
        model.addAttribute("newPengadaan", newPengadaan);
        return "form-add-pengadaan-fasilitas";
    }

    @RequestMapping(value = "/pengadaan-fasilitas/add", method = RequestMethod.POST)
    public String addPengadaanFasilitasSubmit(@ModelAttribute PengadaanFasilitasModel pengadaanFasilitas, Model model, Authentication authentication){
        UserModel user = userService.findByUserName(authentication.getName());
        if(user.getRole().getNama().equalsIgnoreCase("guru")){
            pengadaanFasilitas.setStatus(0);
        }
        else{
            pengadaanFasilitas.setStatus(1);
        }
        pengadaanFasilitas.setUser(user);
        pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
        model.addAttribute("namaPengadaan", pengadaanFasilitas.getNama());
        return "submit-pengadaan-fasilitas";
    }
}
