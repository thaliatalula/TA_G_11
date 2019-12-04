package com.apap.tugasakhir.siruangan.controller;
import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.service.PeminjamanRuanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PeminjamanRuanganController {
    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

    @RequestMapping(value = "/ruangan/peminjaman", method = RequestMethod.GET)
    public String daftarPengajuan(Model model) {
        List<PeminjamanRuanganModel> listPeminjaman = peminjamanRuanganService.getPeminjamanRuanganList();
        model.addAttribute("listPeminjaman", listPeminjaman);
        return "peminjaman-all";
    }
}
