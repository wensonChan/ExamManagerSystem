package com.neu.edu.service;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.ExamScheduleDTO;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ExamScheduleVO;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/13 16:15
 */
public interface ExamScheduleService {
    ResultModel<List<ExamScheduleVO>> findBySelection(ExamScheduleDTO examScheduleDTO);

    ResultModel add(ExamScheduleDTO examScheduleDTO);

    ResultModel deleteById(int course_id) throws BusinessException;

    ResultModel updateById(ExamScheduleDTO examScheduleDTO) throws BusinessException;

}
