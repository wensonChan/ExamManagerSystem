package com.learn.controller;

import com.learn.service.QuestionService;
import com.learn.utils.ResultModel;
import com.learn.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/questions/{id}") // 进入考试页面的题目显示
    public ResultModel<List<QuestionVO>> findAll(@PathVariable("id")int id){
        return questionService.findAll(id);
    }
}
