package com.apap.tugasakhir.siruangan.controller;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.SuratDetail;
import com.apap.tugasakhir.siruangan.restService.PeminjamanRuanganRestService;
import com.apap.tugasakhir.siruangan.service.PeminjamanRuanganService;
import com.apap.tugasakhir.siruangan.service.RoleService;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import com.apap.tugasakhir.siruangan.service.UserService;
import net.bytebuddy.utility.RandomString;
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
    private PeminjamanRuanganRestService peminjamanRuanganRestService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
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

    @RequestMapping(value = "/ruangan/peminjaman/tambah", method = RequestMethod.POST)
    public String addPeminjamanruanganSubmit (@ModelAttribute PeminjamanRuanganModel peminjamanRuangan,
                                              @RequestParam("nomorSurat") String nomorSurat,
                                              Model model,
                                              RedirectAttributes redirect,
                                              Authentication authentication) throws ParseException {
        System.out.println(nomorSurat);
        if (peminjamanRuanganService.canPinjamKapasitas(peminjamanRuangan)) {
            if ( peminjamanRuanganService.canPinjamWaktu(peminjamanRuangan)){
                UserModel user = userService.findByUserName(authentication.getName());
                peminjamanRuangan.setUserPeminjam(user);
                peminjamanRuangan.setDisetujui(false);
                try{
                    SuratDetail suratDetail=peminjamanRuanganRestService.getStatusSurat(nomorSurat).block();
                    if(suratDetail.getJenisSurat().equalsIgnoreCase("Surat Peminjaman Ruangan") && suratDetail.getStatus().equalsIgnoreCase("disetujui")
                        || suratDetail.getStatus().equalsIgnoreCase("diproses") || suratDetail.getStatus().equalsIgnoreCase("selesai") ){
                        peminjamanRuangan.setDisetujui(true);
                        UserModel users=new UserModel();
                        users.setUuid(suratDetail.getIdUser());
                        users.setUsername("SITU - "+ RandomString.make(5));
                        users.setRole(roleService.findByName("Admin TU"));
                        users.setPassword(RandomString.make(20));
                        userService.addUser(users);
                        peminjamanRuangan.setUserPenyetuju(users);
                    }
                }
                catch (Exception e){

                }
                peminjamanRuanganService.addPeminjamanRuangan(peminjamanRuangan);
                redirect.addFlashAttribute("berhasil", "Ruangan Berhasil dipinjam");

                return "redirect:/ruangan/peminjaman";
            }
            else {
                redirect.addFlashAttribute("gagal", "Waktu peminjaman ruangan tidak tersedia");
            }
        }
        else {
            redirect.addFlashAttribute("gagal", "Jumlah peserta melebihi kapasitas ruangan");
        }

        return "redirect:/ruangan/peminjaman/tambah/"+peminjamanRuangan.getRuangan().getId();

    }

}
