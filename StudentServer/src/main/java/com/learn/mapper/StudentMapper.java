package com.learn.mapper;

import com.learn.domain.Student;
import com.learn.vo.StudentVO;

import java.util.List;
import java.util.Map;

public interface StudentMapper {

    StudentVO findAll(int id);

    int updateSome(Student student);

    StudentVO login(Map<String  ,String> map);

    StudentVO idLogin(Map<String  , Student> map);

}
