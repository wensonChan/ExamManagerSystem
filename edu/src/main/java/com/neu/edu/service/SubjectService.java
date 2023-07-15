package com.neu.edu.service;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.SubjectDTO;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.SubjectVO;

import java.util.List;

public interface SubjectService {

    ResultModel<List<SubjectVO>> findAll();

    ResultModel<List<SubjectVO>> findById(int teacher_id);

    ResultModel add(SubjectDTO subjectDTO);

    ResultModel deleteById(int subject_id) throws BusinessException;

    ResultModel updateById(SubjectDTO subjectDTO) throws BusinessException;
}
