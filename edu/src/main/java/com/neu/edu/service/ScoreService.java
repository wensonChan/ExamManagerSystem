package com.neu.edu.service;

import com.neu.edu.dto.ScoreDTO;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ScoreVO;

import java.util.List;

public interface ScoreService {

    ResultModel<List<ScoreVO>> findAll();

    ResultModel updateById(ScoreDTO scoreDTO);
}
