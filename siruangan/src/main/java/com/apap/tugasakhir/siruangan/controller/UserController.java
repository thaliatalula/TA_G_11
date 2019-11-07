package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    // ====Next Development====

//    @Autowired
//    UserService userService;
//
//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    private String addUserSubmit(@ModelAttribute UserModel user, RedirectAttributes redirect){
//        try{
//            userService.addUser(user);
//            redirect.addFlashAttribute("notif", "Akun berhasil ditambah");
//            return "redirect:/";
//        }
//        catch (NullPointerException e){
//            redirect.addFlashAttribute("notifpassword", "Password harus mengandung 1 angka dan 1 huruf dan memiliki minimum 8 karakter");
//            return "redirect:/";
//        }
//
//    }

}
