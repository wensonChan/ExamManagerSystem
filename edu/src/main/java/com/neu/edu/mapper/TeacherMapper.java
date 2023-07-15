package com.neu.edu.mapper;

import com.neu.edu.domain.Teacher;
import com.neu.edu.vo.TeacherVO;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    List<TeacherVO> findAll();
    TeacherVO findById(int id);

    int add(Teacher teacher);

    int deleteById(int id);

    int updateById(Teacher teacher);

    TeacherVO login(Map<String  ,String> map);

    TeacherVO idLogin(Map<String  ,Teacher> map);
}
