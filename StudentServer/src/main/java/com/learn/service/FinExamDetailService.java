package com.learn.service;

import com.learn.dto.FinExamDetailDTO;
import com.learn.utils.ResultModel;
import com.learn.vo.FinExamDetailVO;

import java.util.List;

public interface FinExamDetailService {
    ResultModel<List<FinExamDetailVO>> findAll(FinExamDetailDTO finExamDetailDTO);
}
