package com.neu.edu.mapper;


import com.neu.edu.domain.ExamSchedule;
import com.neu.edu.dto.ExamScheduleDTO;
import com.neu.edu.vo.ExamScheduleVO;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/13 15:53
 */
public interface ExamScheduleMapper {
    List<ExamScheduleVO> findBySelection(ExamScheduleDTO examScheduleDTO);

    List<ExamScheduleVO> findById(int teacherId);

    int add(ExamSchedule examSchedule);

    int deleteById(int course_id);

    int updateById(ExamSchedule examSchedule);

}
