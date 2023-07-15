package com.neu.edu.service.impl;

import com.neu.edu.common.BusinessException;
import com.neu.edu.domain.ExamSchedule;
import com.neu.edu.dto.ExamScheduleDTO;
import com.neu.edu.mapper.ExamScheduleMapper;
import com.neu.edu.service.ExamScheduleService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ExamScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamScheduleServiceImpl implements ExamScheduleService {

    @Autowired
    ExamScheduleMapper examScheduleMapper;

    @Override
    public ResultModel<List<ExamScheduleVO>> findBySelection(ExamScheduleDTO examScheduleDTO) {
        ResultModel<List<ExamScheduleVO>> resultModel = new ResultModel<List<ExamScheduleVO>>();
        List<ExamScheduleVO> ExamScheduleVOList = examScheduleMapper.findBySelection(examScheduleDTO);
        if (ExamScheduleVOList == null) {
            resultModel.setCode(401);
            resultModel.setMsg("查询考试失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询考试成功");
            resultModel.setData(ExamScheduleVOList);
        }
        return resultModel;
    }

    @Override
    public ResultModel add(ExamScheduleDTO examScheduleDTO) {

        ResultModel resultModel = new ResultModel();
        //DTO--domain
        ExamSchedule examSchedule = new ExamSchedule();
        examSchedule.setCourse_id(examScheduleDTO.getCourse_id());
        examSchedule.setExam_name(examScheduleDTO.getExam_name());
        examSchedule.setTeacher_id(examScheduleDTO.getTeacher_id());
        examSchedule.setRoom(examScheduleDTO.getRoom());
        examSchedule.setStart_time(examScheduleDTO.getStart_time());
        examSchedule.setEnd_time(examScheduleDTO.getEnd_time());

        if (examSchedule == null) {
            resultModel.setCode(401);
            resultModel.setMsg("添加考试失败");
        }else {
            examScheduleMapper.add(examSchedule);
            resultModel.setCode(200);
            resultModel.setMsg("添加考试成功");
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteById(int course_id) throws BusinessException {
        ResultModel resultModel = new ResultModel();
        if (examScheduleMapper.deleteById(course_id) <= 0) {
            throw new BusinessException("删除考试失败");
        }
        resultModel.setCode(200);
        resultModel.setMsg("删除考试成功");
        return resultModel;
    }

    @Override
    public ResultModel updateById(ExamScheduleDTO examScheduleDTO) throws BusinessException {

        ResultModel resultModel = new ResultModel();
        ExamSchedule examSchedule1 = new ExamSchedule();
        examSchedule1.setExam_name(examScheduleDTO.getExam_name());
        examSchedule1.setTeacher_id(examScheduleDTO.getTeacher_id());
        examSchedule1.setRoom(examScheduleDTO.getRoom());
        examSchedule1.setStart_time(examScheduleDTO.getStart_time());
        examSchedule1.setEnd_time(examScheduleDTO.getEnd_time());
        examSchedule1.setCourse_id(examScheduleDTO.getCourse_id());
        if (examScheduleMapper.updateById(examSchedule1) <=0 ){
            throw new BusinessException("更新考试失败");
        }
        resultModel.setCode(200);
        resultModel.setMsg("更新考试成功");
        resultModel.setData(examSchedule1);
        return resultModel;
    }
}
