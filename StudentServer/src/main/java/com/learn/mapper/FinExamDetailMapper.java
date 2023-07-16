package com.learn.mapper;

import com.learn.domain.FinExamDetail;
import com.learn.vo.FinExamDetailVO;

import java.util.List;

public interface FinExamDetailMapper {

    List<FinExamDetailVO> findAll(FinExamDetail finExamDetail);

}
