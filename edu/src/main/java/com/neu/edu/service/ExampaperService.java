package com.neu.edu.service;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.ExampaperDTO;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ExampaperVO;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/15 14:13
 */
public interface ExampaperService {
    ResultModel<List<ExampaperVO>> findBySelection(ExampaperDTO exampaperDTO);

    ResultModel add(ExampaperDTO exampaperDTO);

    ResultModel deleteById(int title_id) throws BusinessException;
}
