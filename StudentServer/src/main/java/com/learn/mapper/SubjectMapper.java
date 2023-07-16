package com.learn.mapper;

import com.learn.vo.SubjectVO;

import java.util.List;

public interface SubjectMapper {

    List<SubjectVO> findAll();

    List<SubjectVO> findByName(String name);

}
