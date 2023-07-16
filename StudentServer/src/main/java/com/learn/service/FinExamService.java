package com.learn.service;

import com.learn.utils.ResultModel;
import com.learn.vo.FinExamVO;

import java.util.List;

public interface FinExamService {
    ResultModel<List<FinExamVO>> findAll(int id);
}
