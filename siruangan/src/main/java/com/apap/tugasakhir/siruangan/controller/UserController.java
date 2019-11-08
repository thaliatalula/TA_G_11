package com.apap.tugasakhir.siruangan.controller;

import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.RoleService;
import com.apap.tugasakhir.siruangan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    private String addUserPage(Model model) {
        model.addAttribute("listRole", roleService.findAll());
        return "add-user";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user, RedirectAttributes redirect){
        if(userService.checkIfUsernameTaken(user)){
            redirect.addFlashAttribute("notif", "Username already taken");
            return "redirect:/add-user";
        }
        userService.addUser(user);
        return "redirect:/";

    }

}
