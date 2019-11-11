package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.RoleModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.UserRestService;
import com.apap.tugasakhir.siruangan.service.RoleService;
import com.apap.tugasakhir.siruangan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<RoleModel> listRole= roleService.findAll().subList(2,4);
        model.addAttribute("listRole", listRole );
        return "add-new-user";
    }

    @PostMapping(value = "/add-user")
    private String addUserSubmit(@ModelAttribute UserModel user,
                                 @RequestParam String nama,
                                 @RequestParam String tempatLahir,
                                 @RequestParam String tanggalLahir,
                                 @RequestParam String alamat,
                                 @RequestParam String telepon,
                                 RedirectAttributes redirect) throws ParseException {
        Date tanggalLahirDate= new SimpleDateFormat("yyyy-mm-dd").parse(tanggalLahir);
        if(userService.checkIfUsernameTaken(user)){
            redirect.addFlashAttribute("notif", "Username already taken");
            return "redirect:/add-user";
        }
        userService.addUser(user);
        if(user.getRole().getNama().equals("Guru")){
            String NIG=userService.generateNIG(user, tanggalLahirDate);
            if(userRestService.addGuru(user, NIG, nama, tempatLahir, tanggalLahirDate,alamat, telepon).block().getStatus()=="200"){
                return "redirect:/";
            }
        }
        else{
            String NIS=userService.generateNIS(user, tanggalLahirDate);
            if(userRestService.addSiswa(user, NIS, nama, tempatLahir, tanggalLahirDate,alamat, telepon).block().getStatus()=="200"){
                return "redirect:/";
            }
        }
        return "redirect:/";

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

}
