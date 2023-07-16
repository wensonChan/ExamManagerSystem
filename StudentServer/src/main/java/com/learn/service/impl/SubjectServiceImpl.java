package com.learn.service.impl;

import com.learn.mapper.SubjectMapper;
import com.learn.service.SubjectService;
import com.learn.utils.ResultModel;
import com.learn.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectMapper subjectMapper;


    @Override
    public ResultModel<List<SubjectVO>> findAll() {
        ResultModel<List<SubjectVO>> resultModel = new ResultModel<>();
        List<SubjectVO> subjectList = subjectMapper.findAll();

        if(subjectList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("查询选修课程失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询选修课程成功");
            resultModel.setData(subjectList);
        }
        return resultModel;
    }

    @Override
    public ResultModel<List<SubjectVO>> findByName(String name) {
        ResultModel<List<SubjectVO>> resultModel = new ResultModel<>();
        List<SubjectVO> subjectList = subjectMapper.findByName(name);

        if(subjectList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("该老师所教课程不存在");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询选修课程成功");
            resultModel.setData(subjectList);
        }
        return resultModel;
    }
}
