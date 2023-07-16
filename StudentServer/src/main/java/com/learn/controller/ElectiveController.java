package com.learn.controller;

import com.learn.dto.ElectiveDTO;
import com.learn.service.ElectiveService;
import com.learn.utils.ResultModel;
import com.learn.vo.ElectiveVO;
import com.learn.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ElectiveController {

    @Autowired
    private ElectiveService electiveService;

    @GetMapping("/electives/{id}") //查询选课记录
    public ResultModel<List<ElectiveVO>> findAll(@PathVariable("id")int student_id){
        return electiveService.findAll(student_id);
    }

    @PostMapping ("/electives") //添加选课记录
    public ResultModel add(ElectiveDTO electiveDTO){
        return electiveService.add(electiveDTO);
    }

    @DeleteMapping ("/electives") //删除选课记录（退选）
    public ResultModel deleteById(ElectiveDTO electiveDTO){
        return electiveService.deleteById(electiveDTO);
    }


}

