package com.learn.service.impl;

import com.learn.mapper.ExamArrangementMapper;
import com.learn.service.ExamArrangementService;
import com.learn.utils.ResultModel;
import com.learn.vo.ExamArrangementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamArrangementServiceImpl implements ExamArrangementService {

    @Autowired
    private ExamArrangementMapper examArrangementMapper;

    @Override
    public ResultModel<List<ExamArrangementVO>> findAll(int id) {
        ResultModel<List<ExamArrangementVO>> resultModel = new ResultModel<>();
        List<ExamArrangementVO> examArrangementList = examArrangementMapper.findAll(id);

        if(examArrangementList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("查询考试安排失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询考试安排成功");
            resultModel.setData(examArrangementList);
        }
        return resultModel;
    }
}
