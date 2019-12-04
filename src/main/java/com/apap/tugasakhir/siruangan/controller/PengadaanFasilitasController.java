package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.PengadaanFasilitasModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.PengadaanFasilitasService;
import com.apap.tugasakhir.siruangan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PengadaanFasilitasController {
    @Autowired
    private PengadaanFasilitasService pengadaanFasilitasService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/fasilitas/pengadaan")
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

    @RequestMapping(value = "/fasilitas/pengadaan/tambah", method = RequestMethod.GET)
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

    @RequestMapping(value = "/fasilitas/pengadaan/tambah", method = RequestMethod.POST)
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
///fasilitas/pengadaan/hapus?idPengadaan={idPengadaan}

    @RequestMapping(value="/fasilitas/pengadaan/hapus", method = RequestMethod.POST)
    public String deletePengadaanFasilitas(
            @RequestParam(value = "id") Integer id, Model model, Authentication authentication
    ){
        PengadaanFasilitasModel pengadaanFasilitas = pengadaanFasilitasService.getPengadaanById(id).get();
        model.addAttribute("pengadaanFasilitas", pengadaanFasilitas);
        model.addAttribute("namaPengadaan", pengadaanFasilitas.getNama());
        UserModel user = userService.findByUserName(authentication.getName());

        pengadaanFasilitasService.deletePengadaanFasilitas(pengadaanFasilitas);
        return "delete-pengadaan-fasilitas";

//        if(user.getRole().getNama().equalsIgnoreCase("guru")){
//            if(pengadaanFasilitas.getStatus()==0){
//                pengadaanFasilitasService.deletePengadaanFasilitas(pengadaanFasilitas);
//                return "delete-pengadaan-fasilitas";
//            }
//            else{
//                return "gagal-delete-pengadaan-fasilitas";
//            }
//        }
//        else{
//            pengadaanFasilitasService.deletePengadaanFasilitas(pengadaanFasilitas);
//            return "delete-pengadaan-fasilitas";
//        }
    }
}
