package com.neu.edu.mapper;

import com.neu.edu.domain.Student;
import com.neu.edu.vo.StudentVO;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    List<StudentVO> findAll();
    StudentVO findById(int id);

    int add(Student student);

    int deleteById(int id);

    int updateById(Student student);

    StudentVO login(Map<String  ,String> map);

    StudentVO idLogin(Map<String  , Student> map);

}
