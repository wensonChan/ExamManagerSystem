package com.neu.edu.controller;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.ExampaperDTO;
import com.neu.edu.service.ExampaperService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ExampaperVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/15 15:58
 */
@RestController
@RequestMapping("/exampapers")
public class ExampaperController {
    @Autowired
    private ExampaperService exampaperService;

    @GetMapping("")
    public ResultModel<List<ExampaperVO>> findBySelection(ExampaperDTO exampaperDTO){
        return exampaperService.findBySelection(exampaperDTO);
    }
    @PostMapping("")
    public ResultModel add(ExampaperDTO exampaperDTO){
        return exampaperService.add(exampaperDTO);
    }

    @DeleteMapping("/{id}")
    public ResultModel deleteById(@PathVariable("id") int title_id) throws BusinessException {
        return exampaperService.deleteById(title_id);
    }
}
