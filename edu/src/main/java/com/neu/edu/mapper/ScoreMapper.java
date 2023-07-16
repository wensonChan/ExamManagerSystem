package com.neu.edu.mapper;


import com.neu.edu.domain.Score;
import com.neu.edu.vo.ScoreVO;

import java.util.List;

public interface ScoreMapper {
    List<ScoreVO> findAll();
    List<ScoreVO> find(int id);
    int updateById(Score score);

}
