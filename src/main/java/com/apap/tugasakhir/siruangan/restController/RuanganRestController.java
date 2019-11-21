package com.apap.tugasakhir.siruangan.restController;

import com.apap.tugasakhir.siruangan.model.PeminjamanRuanganModel;
import com.apap.tugasakhir.siruangan.model.RuanganModel;
import com.apap.tugasakhir.siruangan.model.UserModel;
import com.apap.tugasakhir.siruangan.service.PeminjamanRuanganService;
import com.apap.tugasakhir.siruangan.service.RoleServiceImpl;
import com.apap.tugasakhir.siruangan.service.RuanganService;
import com.apap.tugasakhir.siruangan.service.UserServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ruangan")
public class RuanganRestController {
    @Autowired
    private RuanganService ruanganService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PeminjamanRuanganService peminjamanRuanganService;

    @GetMapping("/")
    private List<RuanganModel> getAllRuangan(){
        return ruanganService.getRuanganList();
    }

    @PostMapping("/peminjaman/tambah")
    private PeminjamanRuanganModel addPeminjaman(@Valid @RequestBody PeminjamanRuanganModel peminjamanRuanganModel, BindingResult bindingResult){
        UserModel userNew= new UserModel();
        userNew.setUsername(RandomString.make(35));
        userNew.setRole(roleService.findByName("Guru"));
        userNew.setPassword("password");
        userService.addUser(userNew);
        peminjamanRuanganModel.setUserPeminjam(userNew);
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        else{
            return peminjamanRuanganService.addPeminjamanRUangan(peminjamanRuanganModel);
        }
    }
}