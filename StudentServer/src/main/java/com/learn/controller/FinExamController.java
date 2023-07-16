package com.learn.controller;

import com.learn.service.FinExamService;
import com.learn.utils.ResultModel;
import com.learn.vo.FinExamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FinExamController {

    @Autowired
    private FinExamService finExamService;

    @GetMapping("/finExams/{id}") //查询已完成的考试
    public ResultModel<List<FinExamVO>> findAll(@PathVariable("id") int id){
        return finExamService.findAll(id);
    }

}

