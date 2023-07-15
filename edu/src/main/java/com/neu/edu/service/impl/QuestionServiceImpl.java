package com.neu.edu.service.impl;

import com.neu.edu.common.BusinessException;
import com.neu.edu.domain.Question;
import com.neu.edu.dto.ExamScheduleDTO;
import com.neu.edu.dto.QuestionDTO;
import com.neu.edu.mapper.QuestionMapper;
import com.neu.edu.service.QuestionService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.QuestionVO;
import com.neu.edu.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/15 10:57
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public ResultModel<List<QuestionVO>> findAll() {
        ResultModel<List<QuestionVO>> resultModel = new ResultModel<List<QuestionVO>>();
        List<QuestionVO> questionVOList = questionMapper.findAll();
        if (questionVOList == null) {
            resultModel.setCode(401);
            resultModel.setMsg("查询问题失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询问题成功");
            resultModel.setData(questionVOList);
        }
        return resultModel;
    }

    @Override
    public ResultModel<List<QuestionVO>> findById(int subject_id) {

        ResultModel<List<QuestionVO>> resultModel = new ResultModel<List<QuestionVO>>();
        List<QuestionVO> QuestionVOList = questionMapper.findById(subject_id);
        if (QuestionVOList == null) {
            resultModel.setCode(401);
            resultModel.setMsg("查询问题失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询问题成功");
            resultModel.setData(QuestionVOList);
        }
        return resultModel;
    }

    @Override
    public ResultModel add(QuestionDTO questionDTO) {
        ResultModel resultModel = new ResultModel();
        //DTO--domain
        Question question = new Question();

        question.setTitle(questionDTO.getTitle());
        question.setScore(questionDTO.getScore());
        question.setType(questionDTO.getType());
        question.setAnswer(questionDTO.getAnswer());
        question.setSubject_id(questionDTO.getSubject_id());
        question.setOption(questionDTO.getOption());

        questionMapper.add(question);
        resultModel.setCode(200);
        resultModel.setMsg("添加题目成功");
        return resultModel;
    }

    @Override
    public ResultModel deleteById(int question_id) throws BusinessException {
        ResultModel resultModel = new ResultModel();

        if(questionMapper.deleteById(question_id) <= 0){
            throw new BusinessException("删除题目");
        }
        resultModel.setCode(200);
        resultModel.setMsg("删除题目成功");
        return resultModel;
    }

    @Override
    public ResultModel updateById(QuestionDTO questionDTO) throws BusinessException {
        ResultModel resultModel = new ResultModel();

        Question question = new Question();

        question.setQuestion_id(questionDTO.getQuestion_id());
        question.setTitle(questionDTO.getTitle());
        question.setScore(questionDTO.getScore());
        question.setType(questionDTO.getType());
        question.setAnswer(questionDTO.getAnswer());
        question.setSubject_id(questionDTO.getSubject_id());
        question.setOption(questionDTO.getOption());

        if(questionMapper.updateById(question) <= 0){
            throw new BusinessException("更新题目");
        }
        resultModel.setCode(200);
        resultModel.setMsg("更新题目成功");
        return resultModel;
    }
}
