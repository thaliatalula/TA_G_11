package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RoleModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.GuruDetail;
import com.apap.tugasakhir.siruangan.rest.GuruDetailResp;
import com.apap.tugasakhir.siruangan.rest.SiswaDetail;
import com.apap.tugasakhir.siruangan.rest.SiswaDetailResp;
import com.apap.tugasakhir.siruangan.restService.UserRestService;
import com.apap.tugasakhir.siruangan.service.RoleService;
import com.apap.tugasakhir.siruangan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRestService userRestService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    private String addUserPage(Model model) {
        List<RoleModel> listRole= roleService.findAll();
        model.addAttribute("listRole", listRole );
        return "add-new-user";
    }

    @RequestMapping(value = "/my-profile", method = RequestMethod.GET)
    public String viewProfile(Authentication authentication, Model model){

        UserModel user = userService.findByUserName(authentication.getName());
        SiswaDetail siswa;
        GuruDetail guru;

        if(user.getRole().getNama().equalsIgnoreCase("guru")){
            guru=userRestService.getGuru(user.getUuid()).block().getResult();
            model.addAttribute("guru", guru);
            model.addAttribute("sisivitas", guru.getNama());
        }
        else if(user.getRole().getNama().equalsIgnoreCase("siswa")){
            siswa=userRestService.getSiswa(user.getUuid()).block().getResult();
            model.addAttribute("siswa", siswa);
            model.addAttribute("sisivitas", siswa.getNama());
        }

        model.addAttribute("user", user);
        return "view-user-profile";
    }

    @PostMapping(value = "/add-user")
    private String addUserSubmit(@ModelAttribute UserModel user,
                                 @RequestParam String nama,
                                 @RequestParam String tempatLahir,
                                 @RequestParam String tanggalLahir,
                                 @RequestParam String alamat,
                                 @RequestParam String telepon,
                                 RedirectAttributes redirect) throws ParseException {
        if(userService.checkIfUsernameTaken(user)){
            redirect.addFlashAttribute("usernameGagal", "Username already taken");
            return "redirect:/add-user";
        }
        userService.addUser(user);
        if(user.getRole().getNama().equals("Guru")){
            GuruDetail guru= new GuruDetail();
            Date tanggalLahirDate= new SimpleDateFormat("yyyy-mm-dd").parse(tanggalLahir);
            String NIG=userService.generateNIG(user, tanggalLahirDate);
            guru.setNama(nama);
            guru.setAlamat(alamat);
            guru.setTempatLahir(tempatLahir);
            guru.setTanggalLahir(tanggalLahirDate);
            guru.setTelepon(telepon);
            guru.setNig(NIG);
            if(userRestService.addGuru(user, guru).block().getStatus().equals("200")){
                redirect.addFlashAttribute("berhasil","User berhasil ditambah");
            }
            else {
                redirect.addFlashAttribute("gagal","User gagal ditambah");
            }
            return  "redirect:/add-user";
        }
        else if(user.getRole().getNama().equals("Siswa")){
            SiswaDetail siswa= new SiswaDetail();
            Date tanggalLahirDate= new SimpleDateFormat("yyyy-mm-dd").parse(tanggalLahir);
            String NIS=userService.generateNIS(user, tanggalLahirDate);
            siswa.setNama(nama);
            siswa.setAlamat(alamat);
            siswa.setTempatLahir(tempatLahir);
            siswa.setTanggalLahir(tanggalLahirDate);
            siswa.setTelepon(telepon);
            siswa.setNis(NIS);
            if(userRestService.addSiswa(user, siswa).block().getStatus().equals("200")){
                redirect.addFlashAttribute("berhasil","User berhasil ditambah");
            }
            else {
                redirect.addFlashAttribute("gagal","User gagal ditambah");
            }
            return "redirect:/add-user";
        }
        redirect.addFlashAttribute("berhasil","User berhasil ditambah");
        return "redirect:/add-user";
    }

//    =============================FOR TESTING ADD USER===========================
    @RequestMapping(value = "/add-user/for-test", method = RequestMethod.GET)
    private String addUserTest(Model model) {
        List<RoleModel> listRole= roleService.findAll();
        model.addAttribute("listRole", listRole );
        return "[TESTING]add-new-user";
    }

    @RequestMapping(value = "/add-user/for-test", method = RequestMethod.POST)
    private String addUserSubmitTest(@ModelAttribute UserModel user, RedirectAttributes redirect){
        if(userService.checkIfUsernameTaken(user)){
            redirect.addFlashAttribute("notif", "Username already taken");
            return "redirect:/add-user/for-test";
        }
        userService.addUser(user);

        return "redirect:/";

    }

    /*@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    private String viewUserProfile(@ModelAttribute UserModel)*/

}
