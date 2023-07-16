package com.learn.service.impl;

import com.learn.domain.Elective;
import com.learn.dto.ElectiveDTO;
import com.learn.mapper.ElectiveMapper;
import com.learn.service.ElectiveService;
import com.learn.utils.ResultModel;
import com.learn.vo.ElectiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectiveServiceImpl implements ElectiveService {

    @Autowired
    ElectiveMapper electiveMapper;

    //DTO--domain
    @Autowired
    Elective elective;

    @Override
    public ResultModel<List<ElectiveVO>> findAll(int student_id) {
        ResultModel<List<ElectiveVO>> resultModel = new ResultModel<>();
        List<ElectiveVO> electiveList = electiveMapper.findAll(student_id);

        if(electiveList.isEmpty()){
            resultModel.setCode(404);
            resultModel.setMsg("查询选课记录失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询选课记录成功");
            resultModel.setData(electiveList);
        }
        return resultModel;
    }

    @Override
    public ResultModel add(ElectiveDTO electiveDTO) {
        ResultModel resultModel = new ResultModel();
//        System.out.println(electiveDTO.getStudent_id());
//        System.out.println(electiveDTO.getSubject_id());
        elective.setStudent_id(electiveDTO.getStudent_id());
        elective.setSubject_id(electiveDTO.getSubject_id());
//        System.out.println(elective.getStudent_id());
//        System.out.println(elective.getSubject_id());
        try {
            int result = electiveMapper.add(elective);
            if(result == 0){
                resultModel.setCode(404);
                resultModel.setMsg("添加选课记录失败");
            }else{
                resultModel.setCode(200);
                resultModel.setMsg("添加选课记录成功");
            }
            return resultModel;
        }catch(Exception e){
            resultModel.setCode(404);
            resultModel.setMsg("添加选课记录失败");
            return resultModel;
        }
    }

    @Override
    public ResultModel deleteById(ElectiveDTO electiveDTO) {
        ResultModel resultModel = new ResultModel();
        elective.setStudent_id(electiveDTO.getStudent_id());
        elective.setSubject_id(electiveDTO.getSubject_id());
        System.out.println(elective.getStudent_id());
        System.out.println(elective.getSubject_id());
        int result = electiveMapper.deleteById(elective);
        if(result == 0){
            resultModel.setCode(404);
            resultModel.setMsg("删除选课记录失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("删除选课记录成功");
        }
        return resultModel;

    }
}
