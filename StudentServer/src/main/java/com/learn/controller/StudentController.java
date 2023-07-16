package com.learn.controller;

import com.learn.dto.StudentDTO;
import com.learn.service.StudentService;
import com.learn.utils.ResultModel;
import com.learn.vo.StudentVO;
import com.learn.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{id}") //查询个人信息
    public ResultModel<StudentVO> findAll(@PathVariable("id")int id){
        return studentService.findAll(id);
    }

    @PutMapping("/students") //修改个人信息
    public ResultModel updateSome(StudentDTO studentDTO){
        return studentService.updateSome(studentDTO);
    }

}

