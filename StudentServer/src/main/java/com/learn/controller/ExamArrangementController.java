package com.learn.controller;

import com.learn.service.ExamArrangementService;
import com.learn.utils.ResultModel;
import com.learn.vo.ExamArrangementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamArrangementController {

    @Autowired
    private ExamArrangementService examArrangementService;

    @GetMapping("/examArrangements/{id}") // 查询考试安排
    public ResultModel<List<ExamArrangementVO>> findAll(@PathVariable("id") int id){
        return examArrangementService.findAll(id);
    }


}

