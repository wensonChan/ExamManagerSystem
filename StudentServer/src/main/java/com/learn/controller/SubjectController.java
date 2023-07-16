package com.learn.controller;

import com.learn.service.SubjectService;
import com.learn.utils.ResultModel;
import com.learn.vo.SubjectVO;
import com.learn.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects") // 查询课程列表并进行选课
    public ResultModel<List<SubjectVO>> findAll(){
        return subjectService.findAll();
    }

    @GetMapping("/subjects/{name}") // 根据老师名字查询课程
    public ResultModel<List<SubjectVO>> findByName(@PathVariable("name") String name){
        return subjectService.findByName(name);
    }





    @PostMapping("/test")
    public String Test(TestVO testVO){
        System.out.println(testVO.getName());
        return testVO.getName();
    }


}

