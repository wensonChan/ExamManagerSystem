package com.learn.service;

import com.learn.domain.Elective;
import com.learn.dto.ElectiveDTO;
import com.learn.utils.ResultModel;
import com.learn.vo.ElectiveVO;

import java.util.List;

public interface ElectiveService {
    ResultModel<List<ElectiveVO>> findAll(int student_id);

    ResultModel add(ElectiveDTO electiveDTO);

    ResultModel deleteById(ElectiveDTO electiveDTO);
}
