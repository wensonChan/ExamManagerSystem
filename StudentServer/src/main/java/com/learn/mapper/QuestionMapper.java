package com.learn.mapper;

import com.learn.vo.QuestionVO;

import java.util.List;

public interface QuestionMapper {

    List<QuestionVO> findAll(int id);

}
