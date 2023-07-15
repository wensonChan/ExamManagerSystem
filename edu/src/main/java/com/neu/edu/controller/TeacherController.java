package com.neu.edu.controller;

import com.neu.edu.dto.TeacherDTO;
import com.neu.edu.service.TeacherService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    public ResultModel<List<TeacherVO>> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public ResultModel findById(@PathVariable("id") int id){
        return teacherService.findById(id);
    }

    @PostMapping("")
    public ResultModel add(TeacherDTO teacherDTO){
        return teacherService.add(teacherDTO);
    }

    @DeleteMapping("/{id}")
    public ResultModel deleteById(@PathVariable("id") int id){
        return teacherService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResultModel updateById(TeacherDTO teacherDTO){
        return teacherService.updateById(teacherDTO);
    }

}
