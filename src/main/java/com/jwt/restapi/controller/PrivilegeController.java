package com.jwt.restapi.controller;

import com.jwt.restapi.entity.Privilege;
import com.jwt.restapi.service.PrivilegeService;
import com.jwt.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PrivilegeController {


    @Autowired
    private PrivilegeService privilegeService;

    @Autowired
   // private UserService userService;

    @GetMapping("/privileges")
    public List<Privilege> parameters() {
        return privilegeService.findAll();
    }

    @GetMapping("/privilege/{id}")
    public Privilege getById(@PathVariable Integer id) {
        return privilegeService.getById(id);
    }

    @PutMapping("/privilege/{id}")
    public Privilege updatePrivilege(@RequestBody() Privilege privilege, @PathVariable("id") Long id){
        return privilegeService.save(privilege);
    }

    @PostMapping("/privileges")
    public Privilege save(Privilege privilege) {
        return privilegeService.save(privilege);
    }

    @DeleteMapping("/privilege/delete/{id}")
    public void delete(@PathVariable Integer id) {
        privilegeService.delete(id);
    }
}

