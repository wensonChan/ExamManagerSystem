package com.learn.mapper;

import com.learn.domain.ExamAnswer;
import com.learn.vo.CheckVO;

public interface ExamAnswerMapper {

    CheckVO checkRepeat(ExamAnswer examAnswer);
    int saveAnswer(ExamAnswer examAnswer);
    int updateAnswer(ExamAnswer examAnswer);

}
