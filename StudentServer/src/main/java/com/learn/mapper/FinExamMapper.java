package com.learn.mapper;

import com.learn.vo.FinExamVO;

import java.util.List;

public interface FinExamMapper {

    List<FinExamVO> findAll(int id);

}
