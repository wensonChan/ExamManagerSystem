package com.neu.edu.mapper;


import com.neu.edu.domain.ExamSchedule;
import com.neu.edu.domain.Exampaper;
import com.neu.edu.dto.ExampaperDTO;
import com.neu.edu.vo.ExampaperVO;


import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/15 15:28
 */
public interface ExampaperMapper {
    List<ExampaperVO> findBySelection(ExampaperDTO exampaperDTO);

    int add(Exampaper exampaper);

    int deleteById(int title_id);
}
