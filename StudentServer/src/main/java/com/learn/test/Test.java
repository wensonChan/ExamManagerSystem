package com.learn.test;

import com.learn.service.SubjectService;
import com.learn.service.impl.SubjectServiceImpl;
import com.learn.utils.ResultModel;
import com.learn.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SubjectServiceImpl subjectService = new SubjectServiceImpl();
        ResultModel<List<SubjectVO>> resultModel = subjectService.findAll();
        System.out.println(resultModel);

    }
}