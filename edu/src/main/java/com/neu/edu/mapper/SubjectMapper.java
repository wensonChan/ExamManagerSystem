package com.neu.edu.mapper;

import com.neu.edu.domain.Subject;
import com.neu.edu.vo.SubjectVO;

import java.util.List;

public interface SubjectMapper {

    List<SubjectVO> findAll();

    List<SubjectVO> findById(int teacher_id);
    int add(Subject subject);

    int deleteById(int subject_id);

    int updateById(Subject subject);
}
