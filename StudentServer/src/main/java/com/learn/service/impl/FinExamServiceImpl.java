package com.learn.service.impl;

import com.learn.mapper.FinExamMapper;
import com.learn.service.FinExamService;
import com.learn.utils.ResultModel;
import com.learn.vo.FinExamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinExamServiceImpl implements FinExamService {

    @Autowired
    FinExamMapper finExamMapper;


    @Override
    public ResultModel<List<FinExamVO>> findAll(int id) {
        ResultModel<List<FinExamVO>> resultModel = new ResultModel<>();
        List<FinExamVO> finExamList = finExamMapper.findAll(id);

        if(finExamList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("查询试卷失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询试卷成功");
            resultModel.setData(finExamList);
        }
        return resultModel;
    }

}
