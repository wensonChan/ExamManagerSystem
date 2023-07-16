package com.learn.service.impl;

import com.learn.domain.ExamAnswer;
import com.learn.dto.ExamAnswerDTO;
import com.learn.mapper.ExamAnswerMapper;
import com.learn.service.ExamAnswerService;
import com.learn.utils.ResultModel;
import com.learn.vo.CheckVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamAnswerServiceImpl implements ExamAnswerService {
    @Autowired
    ExamAnswer examAnswer;
    @Autowired
    ExamAnswerMapper examAnswerMapper;

    @Override
    public ResultModel saveAnswer(ExamAnswerDTO examAnswerDTO) {
        ResultModel resultModel = new ResultModel();
//        System.out.println(examAnswerDTO.getStudent_id());
        examAnswer.setStudent_id(examAnswerDTO.getStudent_id());
        examAnswer.setCourse_id(examAnswerDTO.getCourse_id());
        examAnswer.setTitle_id(examAnswerDTO.getTitle_id());
        examAnswer.setStu_answer(examAnswerDTO.getStu_answer());
        CheckVO checkVO = examAnswerMapper.checkRepeat(examAnswer);
        if(checkVO == null){
            examAnswerMapper.saveAnswer(examAnswer);
            resultModel.setCode(200);
            resultModel.setMsg("添加答案成功");
        }else {
            try {
                examAnswerMapper.updateAnswer(examAnswer);
                resultModel.setCode(200);
                resultModel.setMsg("更新答案成功");
            } catch (Exception e) {
                resultModel.setCode(404);
                resultModel.setMsg("更新答案失败");
                return resultModel;
            }
        }
        return resultModel;
    }
}
