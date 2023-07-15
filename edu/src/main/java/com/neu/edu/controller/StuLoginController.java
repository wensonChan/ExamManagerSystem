package com.neu.edu.controller;

import com.neu.edu.service.StudentService;
import com.neu.edu.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StuLoginController {

    @Autowired
    StudentService studentService;

    /**
     * 通过用户名（String）和密码（String）登录
     * @param stu_name
     * @param password
     * @return
     */
    @PostMapping("/student/login")
    public ResultModel<String> login_student(String stu_name, String password){
        System.out.println("student login");
        return studentService.login(stu_name,password);
    }

    /**
     * 通过学号号（int）和密码（String）登录
     * @param student_id
     * @param password
     * @return
     */
    @PostMapping ("/student/idLogin")
    public ResultModel<String> login_teacherById(int student_id, String password){
        System.out.println("student loginById");
        return studentService.loginById(student_id,password);
    }
}
