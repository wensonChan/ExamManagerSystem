package com.learn.service;

import com.learn.dto.ExamAnswerDTO;
import com.learn.utils.ResultModel;

public interface ExamAnswerService {
    ResultModel saveAnswer(ExamAnswerDTO examAnswerDTO);
}
