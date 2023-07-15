package com.neu.edu.service;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.QuestionDTO;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.QuestionVO;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/15 10:58
 */
public interface QuestionService {
    ResultModel<List<QuestionVO>> findAll();
    ResultModel<List<QuestionVO>> findById(int teacher_id);
    ResultModel add(QuestionDTO questionDTO);
    ResultModel deleteById(int question_id) throws BusinessException;
    ResultModel updateById(QuestionDTO questionDTO) throws BusinessException;
}
