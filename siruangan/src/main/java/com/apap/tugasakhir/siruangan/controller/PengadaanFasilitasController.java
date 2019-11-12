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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PengadaanFasilitasController {
    @Autowired
    private PengadaanFasilitasService pengadaanFasilitasService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/pengadaan-fasilitas/daftar-pengajuan")
    public String daftarPengajuan(Model model, Authentication authentication){
        UserModel user= userService.findByUserName(authentication.getName());
        if(user.getRole().getNama().equalsIgnoreCase("guru")){
            List<PengadaanFasilitasModel> daftarPengajuanGuru =pengadaanFasilitasService.findAllPengajuanByIdUser(user.getId());
            model.addAttribute("daftarPengajuan", daftarPengajuanGuru);
        }
        else{
            List<PengadaanFasilitasModel> daftarPengajuan = pengadaanFasilitasService.getPengadaanFasilitasList();
            model.addAttribute("daftarPengajuan", daftarPengajuan);
        }
        return "daftar-pengajuan-fasilitas";
    }

    @RequestMapping(value = "/pengadaan-fasilitas/add", method = RequestMethod.GET)
    public String addPengadaanFasilitasForm(Model model){
        PengadaanFasilitasModel newPengadaan = new PengadaanFasilitasModel();
        model.addAttribute("newPengadaan", newPengadaan);
        return "form-add-pengadaan-fasilitas";
    }

    @RequestMapping(value = "/pengadaan-fasilitas/berhasil", method = RequestMethod.POST)
    public String addPengadaanFasilitasSubmit(@ModelAttribute PengadaanFasilitasModel pengadaanFasilitas, Model model){
        pengadaanFasilitasService.addPengadaanFasilitas(pengadaanFasilitas);
        model.addAttribute("namaPengadaan", pengadaanFasilitas.getNama());
        return "submit-pengadaan-fasilitas";
    }
}
