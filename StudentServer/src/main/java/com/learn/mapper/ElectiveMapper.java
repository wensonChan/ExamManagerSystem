package com.learn.mapper;

import com.learn.domain.Elective;
import com.learn.vo.ElectiveVO;

import java.util.List;

public interface ElectiveMapper {

    List<ElectiveVO> findAll(int student_id);

    int add(Elective elective);

    int deleteById(Elective elective);

}
