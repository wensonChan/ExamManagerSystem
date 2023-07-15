package com.neu.edu.controller;


import com.neu.edu.service.AdminService;
import com.neu.edu.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin/login")
    public ResultModel<String> login_student(String t_admin, String password){
        System.out.println("admin login");

        return adminService.login(t_admin,password);
    }
}
