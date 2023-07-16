package com.learn.service.impl;

import com.learn.domain.FinExamDetail;
import com.learn.dto.FinExamDetailDTO;
import com.learn.mapper.FinExamDetailMapper;
import com.learn.service.FinExamDetailService;
import com.learn.utils.ResultModel;
import com.learn.vo.FinExamDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinExamDetailServiceImpl implements FinExamDetailService {
    @Autowired
    FinExamDetailMapper finExamDetailMapper;
    @Autowired
    FinExamDetail finExamDetail;


    @Override
    public ResultModel<List<FinExamDetailVO>> findAll(FinExamDetailDTO finExamDetailDTO) {
        ResultModel<List<FinExamDetailVO>> resultModel = new ResultModel<>();
        finExamDetail.setStudent_id(finExamDetailDTO.getStudent_id());
        finExamDetail.setSubject_id(finExamDetailDTO.getSubject_id());

        List<FinExamDetailVO> finExamDetailList = finExamDetailMapper.findAll(finExamDetail);

        if(finExamDetailList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("进入试卷详情失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("进入试卷详情成功");
            resultModel.setData(finExamDetailList);
        }
        return resultModel;
    }
}
