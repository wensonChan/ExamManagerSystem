package com.learn.controller;

import com.learn.dto.FinExamDetailDTO;
import com.learn.service.FinExamDetailService;
import com.learn.service.FinExamService;
import com.learn.utils.ResultModel;
import com.learn.vo.FinExamDetailVO;
import com.learn.vo.FinExamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FinExamDetailController {

    @Autowired
    private FinExamDetailService finExamDetailService;

    @PostMapping("/finExamDetails/") //进入试卷详情页
    public ResultModel<List<FinExamDetailVO>> findAll(FinExamDetailDTO finExamDetailDTO){
        return finExamDetailService.findAll(finExamDetailDTO);
    }

}

