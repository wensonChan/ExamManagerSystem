package com.neu.edu.service.impl;


import com.neu.edu.domain.Score;
import com.neu.edu.dto.ScoreDTO;
import com.neu.edu.mapper.ScoreMapper;
import com.neu.edu.service.ScoreService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{


    @Autowired
    ScoreMapper scoreMapper;


    @Override
    public ResultModel<List<ScoreVO>> findAll(){
        ResultModel<List<ScoreVO>> resultModel = new ResultModel<List<ScoreVO>>();
        List<ScoreVO> scoreVOList = scoreMapper.findAll();
        resultModel.setCode(200);
        resultModel.setMsg("查询学生成绩成功");
        resultModel.setData(scoreVOList);
        return resultModel;
    }

    @Override
    public ResultModel updateById(ScoreDTO scoreDTO) {

        ResultModel resultModel = new ResultModel();
        //DTO--domain
        Score score = new Score();
        score.setStudent_id(scoreDTO.getStudent_id());
        score.setScore(scoreDTO.getScore());
        score.setSubject_id(scoreDTO.getSubject_id());

        int res = 0;
        try {
            res = scoreMapper.updateById(score);
            if(res==0){
                resultModel.setCode(401);
                resultModel.setMsg("更新失败，成绩不存在");
            }
            else {
                resultModel.setCode(200);
                resultModel.setMsg("更新成绩成功");
                resultModel.setData(score.getScore());
            }
        } catch (Exception e) {
            resultModel.setCode(401);
            resultModel.setMsg("成绩不可更改");
            throw new RuntimeException(e);
        }finally {
            return resultModel;
        }
    }
}
