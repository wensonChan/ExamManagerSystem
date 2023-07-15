package com.neu.edu.controller;

import com.neu.edu.dto.StudentDTO;
import com.neu.edu.service.StudentService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResultModel<List<StudentVO>> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResultModel findById(@PathVariable("id") int id){
        return studentService.findById(id);
    }

    @PostMapping("")
    public ResultModel add(StudentDTO studentDTO){
        return studentService.add(studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResultModel deleteById(@PathVariable("id") int id){
        return studentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResultModel updateById(StudentDTO studentDTO){
        return studentService.updateById(studentDTO);
    }

}
