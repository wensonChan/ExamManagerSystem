package com.learn.service;

import com.learn.domain.Student;
import com.learn.dto.StudentDTO;
import com.learn.utils.ResultModel;
import com.learn.vo.StudentVO;

import java.util.List;

public interface StudentService {
    ResultModel<StudentVO> findAll(int id);

    ResultModel updateSome(StudentDTO studentDTO);

    ResultModel<String> login(String name, String password);

    ResultModel<String> loginById(int student_id, String password);

}
