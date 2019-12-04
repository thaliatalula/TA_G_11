package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.FasilitasModel;
import com.apap.tugasakhir.siruangan.model.RoleModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.rest.*;
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

    @RequestMapping(value = "/user/tambah", method = RequestMethod.GET)
    private String addUserPage(Model model) {
        List<RoleModel> listRole= roleService.findAll().subList(1,4);
        model.addAttribute("listRole", listRole );
        return "add-new-user";
    }

    @RequestMapping(value = "/user/profil", method = RequestMethod.GET)
    public String viewProfile(Authentication authentication, Model model){

        UserModel user = userService.findByUserName(authentication.getName());
        UsersDetail siswa;
        UsersDetail guru;
        UsersDetail admin;

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
        else if(user.getRole().getNama().equalsIgnoreCase("Admin TU")){
            try {
                admin = userRestService.getAdmin(user.getUuid()).block().getResult();
                model.addAttribute("admin", admin);
                model.addAttribute("sisivitas", admin.getNama());
            }
            catch (Exception e){

            }
        }

        model.addAttribute("user", user);
        return "view-user-profile";
    }

    @PostMapping(value = "/user/tambah")
    private String addUserSubmit(@ModelAttribute UserModel user,@ModelAttribute UsersDetail usersDetail,
                                 RedirectAttributes redirect) throws ParseException {
        if(userService.checkIfUsernameTaken(user)){
            redirect.addFlashAttribute("usernameGagal", "Username already taken");
            return "redirect:/user/tambah";
        }
        userService.addUser(user);
        if(user.getRole().getNama().equals("Guru")){
            String NIG=userService.generateNIG(user, usersDetail.getTanggalLahir());
            usersDetail.setNig(NIG);
            if(userRestService.addGuru(user, usersDetail).block().getStatus().equals("200")){
                redirect.addFlashAttribute("berhasil","User berhasil ditambah");
            }
            else {
                redirect.addFlashAttribute("gagal","User gagal ditambah");
            }
            return  "redirect:/user/tambah";
        }
        if(user.getRole().getNama().equals("Siswa")){
            String NIS=userService.generateNIS(user, usersDetail.getTanggalLahir());
            usersDetail.setNis(NIS);
            if(userRestService.addSiswa(user, usersDetail).block().getStatus().equals("200")){
                redirect.addFlashAttribute("berhasil","User berhasil ditambah");
            }
            else {
                redirect.addFlashAttribute("gagal","User gagal ditambah");
            }
            return "redirect:/user/tambah";
        }
        if(user.getRole().getNama().equals("Admin TU")){
            String NIP=userService.generateNIP(user, usersDetail.getTanggalLahir());
            usersDetail.setNip(NIP);
            if(userRestService.addAdmin(user, usersDetail).block().getStatus().equals("200")){
                redirect.addFlashAttribute("berhasil","User berhasil ditambah");
            }
            else {
                redirect.addFlashAttribute("gagal","User gagal ditambah");
            }
            return "redirect:/user/tambah";
        }
        redirect.addFlashAttribute("berhasil","User berhasil ditambah");
        return "redirect:/user/tambah";
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