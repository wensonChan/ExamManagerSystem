package com.learn.mapper;

import com.learn.vo.ExamArrangementVO;

import java.util.List;

public interface ExamArrangementMapper {

    List<ExamArrangementVO> findAll(int id);

}
