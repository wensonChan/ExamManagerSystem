package com.neu.edu.controller;

import com.neu.edu.service.TeacherService;
import com.neu.edu.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherLoginController {

    @Autowired
    TeacherService teacherService;

    /**
     * 通过用户名（String）和密码（String）登录
     * @param name
     * @param password
     * @return
     */
    @PostMapping ("/teacher/login")
    public ResultModel<String> login_teacher(String name, String password){
        System.out.println("teacher login");
        return teacherService.login(name,password);
    }

    /**
     * 通过教工号（int）和密码（String）登录
     * @param teacher_id
     * @param password
     * @return
     */
    @PostMapping ("/teacher/idLogin")
    public ResultModel<String> login_teacherById(int teacher_id, String password){
        System.out.println("teacher loginById");
        return teacherService.loginById(teacher_id,password);
    }

}
