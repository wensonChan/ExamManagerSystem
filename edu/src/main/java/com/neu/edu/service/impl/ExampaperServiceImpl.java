package com.neu.edu.service.impl;

import com.neu.edu.common.BusinessException;
import com.neu.edu.domain.Exampaper;
import com.neu.edu.dto.ExampaperDTO;
import com.neu.edu.mapper.ExampaperMapper;
import com.neu.edu.service.ExampaperService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.ExampaperVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/15 14:12
 */
@Service
public class ExampaperServiceImpl implements ExampaperService {

    @Autowired
    ExampaperMapper exampaperMapper;
    @Override
    public ResultModel<List<ExampaperVO>> findBySelection(ExampaperDTO exampaperDTO) {

        ResultModel<List<ExampaperVO>> resultModel = new ResultModel<List<ExampaperVO>>();
        List<ExampaperVO> ExampaperVOList = exampaperMapper.findBySelection(exampaperDTO);
        if (ExampaperVOList == null) {
            resultModel.setCode(401);
            resultModel.setMsg("查询试卷失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询试卷成功");
            resultModel.setData(ExampaperVOList);
        }
        return resultModel;
    }

    @Override
    public ResultModel add(ExampaperDTO exampaperDTO) {

        ResultModel resultModel = new ResultModel();

        //DTO--domain
        Exampaper exampaper = new Exampaper();
        exampaper.setCourse_id(exampaperDTO.getCourse_id());
        exampaper.setTitle_id(exampaperDTO.getTitle_id());

        System.out.println(exampaper.getCourse_id());
        System.out.println(exampaper.getTitle_id());

        if(exampaperMapper.add(exampaper)==0){
            resultModel.setCode(401);
            resultModel.setMsg("试卷添加题目失败,该题目已存在");

        }else {
            resultModel.setCode(200);
            resultModel.setMsg("试卷添加题目成功");
            resultModel.setData(exampaper.getTitle_id());
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteById(int title_id) throws BusinessException {
        ResultModel resultModel = new ResultModel();
        if (exampaperMapper.deleteById(title_id) <= 0) {
            throw new BusinessException("删除考试失败");
        }
        resultModel.setCode(200);
        resultModel.setMsg("删除考试成功");
        return resultModel;
    }
}
