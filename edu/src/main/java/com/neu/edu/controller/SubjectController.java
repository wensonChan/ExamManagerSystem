package com.neu.edu.controller;

import com.neu.edu.common.BusinessException;
import com.neu.edu.dto.SubjectDTO;
import com.neu.edu.service.SubjectService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.SubjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("")
    public ResultModel<List<SubjectVO>> findAll(){
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public ResultModel<List<SubjectVO>> findById(@PathVariable("id") int teacher_id){
        return subjectService.findById(teacher_id);
    }

    @PostMapping("")
    public ResultModel add(SubjectDTO subjectDTO){
        return subjectService.add(subjectDTO);
    }

    @DeleteMapping("/{id}")
    public ResultModel deleteById(@PathVariable("id") int subject_id)throws BusinessException {
        return subjectService.deleteById(subject_id);
    }

    @PutMapping("/{subject_id}")
    public ResultModel updateById(@PathVariable("subject_id")int subject_id, SubjectDTO subjectDTO)throws BusinessException{
        return subjectService.updateById(subjectDTO);
    }

}
