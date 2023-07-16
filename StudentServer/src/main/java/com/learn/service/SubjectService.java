package com.learn.service;

import com.learn.utils.ResultModel;
import com.learn.vo.SubjectVO;

import java.util.List;

public interface SubjectService {
    ResultModel<List<SubjectVO>> findAll();
    ResultModel<List<SubjectVO>> findByName(String name);
}
