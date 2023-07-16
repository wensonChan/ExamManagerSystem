package com.neu.edu.controller;

import com.neu.edu.dto.ScoreDTO;
import com.neu.edu.service.ScoreService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("")
    public ResultModel<List<ScoreVO>> findAll(){
        return scoreService.findAll();
    }

    @GetMapping("/{id}")
    public ResultModel<List<ScoreVO>> find(@PathVariable("id") int id){
        return scoreService.find(id);
    }

    @PutMapping("/{id}")
    public ResultModel updateById(@PathVariable("id") int id, ScoreDTO scoreDTO){
        return scoreService.updateById(scoreDTO);
    }


}
