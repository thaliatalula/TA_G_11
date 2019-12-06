package com.apap.tugasakhir.siruangan.controller;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.PeminjamanRuanganService;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import com.apap.tugasakhir.siruangan.service.UserService;
import net.bytebuddy.implementation.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;

@Controller
public class PeminjamanRuanganController {

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

    @Autowired
    private UserService userService;

    @Autowired
    private RuanganService ruanganService;

    @RequestMapping(value = "/ruangan/peminjaman", method = RequestMethod.GET)
    public String daftarPengajuan(Model model, Authentication authentication) {
        UserModel user= userService.findByUserName(authentication.getName());
        List<PeminjamanRuanganModel> listPeminjaman = peminjamanRuanganService.getPeminjamanRuanganList();
        model.addAttribute("listPeminjaman", listPeminjaman);
        model.addAttribute("user", user);

        if (user.getRole().getNama().equals("Admin TU")) {
            return "peminjaman-all-admin";
        }
        else {
            return "peminjaman-all";
        }
    }

    @RequestMapping(value = "/ruangan/peminjaman/tambah/{idRuangan}", method = RequestMethod.GET)
    public String addPeminjamanRuanganForm (@PathVariable int idRuangan, Model model, Authentication authentication) {
        UserModel user= userService.findByUserName(authentication.getName());
        RuanganModel ruangan = ruanganService.getRuanganById(idRuangan);
        model.addAttribute("user", user);
        model.addAttribute("ruangan", ruangan);
        return "form-add-peminjaman-ruangan";
    }

    @RequestMapping(value = "/ruangan/peminjaman/tambah/{idRuangan}", method = RequestMethod.POST)
    public String addPeminjamanruanganSubmit (@PathVariable int idRuangan,
                                              @ModelAttribute PeminjamanRuanganModel peminjamanRuangan,
                                              Model model,
                                              RedirectAttributes redirect,
                                              Authentication authentication) throws ParseException {


        if (peminjamanRuanganService.canPinjamKapasitas(peminjamanRuangan)) {
            if ( peminjamanRuanganService.canPinjamWaktu(peminjamanRuangan)){
                UserModel user = userService.findByUserName(authentication.getName());
                peminjamanRuangan.setUserPeminjam(user);
                peminjamanRuangan.setDisetujui(false);
                peminjamanRuangan.setRuangan(ruanganService.getRuanganById(idRuangan));
                peminjamanRuanganService.addPeminjamanRuangan(peminjamanRuangan);
                redirect.addFlashAttribute("berhasil", "Ruangan Berhasil dipinjam");
            }
            else {
                redirect.addFlashAttribute("gagal", "Waktu peminjaman ruangan tidak tersedia");
            }
        }
        else {
            redirect.addFlashAttribute("gagal", "Jumlah peserta melebihi kapasitas ruangan");
        }

        return "redirect:/ruangan/peminjaman";
    }


    @RequestMapping(value = {"/ruangan/peminjaman/ubah"},
            method = RequestMethod.POST)
    public String UbahStatusPersetujuanPeminjamanSubmit(
            @RequestParam("idPeminjaman") int idPeminjaman,
            @RequestParam("status") boolean status,
            @ModelAttribute PeminjamanRuanganModel peminjamanRuangan,
            Model model,
            Authentication authentication
    ) {
        PeminjamanRuanganModel newpeminjamanRuanganModel=peminjamanRuanganService.getPeminjamanByIdPeminjaman(idPeminjaman);
        UserModel user = userService.findByUserName(authentication.getName());
        newpeminjamanRuanganModel.setUserPenyetuju(user);
        newpeminjamanRuanganModel.setDisetujui(peminjamanRuangan.isDisetujui());
        model.addAttribute("peminjaman", newpeminjamanRuanganModel);

        return "redirect:/ruangan/peminjaman";
    }

}


