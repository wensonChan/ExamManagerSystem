package com.learn.service;

import com.learn.utils.ResultModel;
import com.learn.vo.ExamArrangementVO;

import java.util.List;

public interface ExamArrangementService {
    ResultModel<List<ExamArrangementVO>> findAll(int id);
}
