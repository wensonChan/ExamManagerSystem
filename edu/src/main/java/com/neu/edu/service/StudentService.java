package com.neu.edu.service;

import com.neu.edu.dto.StudentDTO;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.StudentVO;

import java.util.List;

public interface StudentService {
    
    ResultModel<List<StudentVO>> findAll();
    ResultModel findById(int id);

    ResultModel add(StudentDTO studentDTO);
    ResultModel deleteById(int id);
    ResultModel updateById(StudentDTO studentDTO);
    ResultModel<String> login(String name, String password);

    ResultModel<String> loginById(int student_id, String password);
}
