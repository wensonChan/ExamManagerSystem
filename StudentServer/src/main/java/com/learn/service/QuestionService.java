package com.learn.service;

import com.learn.utils.ResultModel;
import com.learn.vo.QuestionVO;

import java.util.List;

public interface QuestionService {

    ResultModel<List<QuestionVO>> findAll(int id);

}
