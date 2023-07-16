package com.learn.service.impl;

import com.learn.mapper.QuestionMapper;
import com.learn.service.QuestionService;
import com.learn.utils.ResultModel;
import com.learn.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;


    @Override
    public ResultModel<List<QuestionVO>> findAll(int id) {
        ResultModel<List<QuestionVO>> resultModel = new ResultModel<>();
        List<QuestionVO> questionVOList = questionMapper.findAll(id);
        if(questionVOList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("当前不是考试时间");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("显示试卷成功");
            resultModel.setData(questionVOList);
        }
        return resultModel;
    }

}
