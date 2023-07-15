package com.neu.edu.controller;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.ExamScheduleDTO;
import com.neu.edu.service.ExamScheduleService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ExamScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ExamScheduleController {

    @Autowired
    private ExamScheduleService examScheduleService;

    @GetMapping("")
    public ResultModel<List<ExamScheduleVO>> findBySelection(ExamScheduleDTO examScheduleDTO){
        return examScheduleService.findBySelection(examScheduleDTO);
    }

    @PostMapping("")
    public ResultModel add(ExamScheduleDTO examScheduleDTO){
        return examScheduleService.add(examScheduleDTO);
    }

    @DeleteMapping("/{id}")
    public ResultModel deleteById(@PathVariable("id") int course_id) throws BusinessException {
        return examScheduleService.deleteById(course_id);
    }

    @PutMapping("/{course_id}")
    public ResultModel updateById(@PathVariable("course_id") int course_id,ExamScheduleDTO examScheduleDTO) throws BusinessException{
        return examScheduleService.updateById(examScheduleDTO);
    }
}
