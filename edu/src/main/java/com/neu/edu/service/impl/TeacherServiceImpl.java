package com.neu.edu.service.impl;

import com.neu.edu.domain.Teacher;
import com.neu.edu.dto.TeacherDTO;
import com.neu.edu.mapper.TeacherMapper;
import com.neu.edu.service.TeacherService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;


    @Override
    public ResultModel<List<TeacherVO>> findAll(){
        ResultModel<List<TeacherVO>> resultModel = new ResultModel<List<TeacherVO>>();
        List<TeacherVO> teacherVOList = teacherMapper.findAll();
        resultModel.setCode(200);
        resultModel.setMsg("查询老师成功");
        resultModel.setData(teacherVOList);
        return resultModel;
    }

    @Override
    public ResultModel findById(int id){
        ResultModel resultModel = new ResultModel();
        TeacherVO teacherVO = teacherMapper.findById(id);

        if(teacherVO==null){
            resultModel.setCode(401);
            resultModel.setMsg("该老师不存在");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("查询老师成功");
            resultModel.setData(teacherVO);
        }

        return resultModel;
    }


    @Override
    public ResultModel add(TeacherDTO teacherDTO) {
        ResultModel resultModel = new ResultModel();

        //DTO--domain
        Teacher teacher = new Teacher();
        //teacher.setTeacher_id(teacherDTO.getTeacher_id());
        teacher.setName(teacherDTO.getName());
        teacher.setPassword(teacherDTO.getPassword());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setMail(teacherDTO.getMail());
        if(teacherMapper.add(teacher)==0){
            resultModel.setCode(401);
            resultModel.setMsg("添加老师失败");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("添加老师成功");
            //resultModel.setData(teacher);
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteById(int id) {
        ResultModel resultModel = new ResultModel();
        int res = 0;

        try {
            res = teacherMapper.deleteById(id);
            if(res==0){
                resultModel.setCode(401);
                resultModel.setMsg("删除老师失败");
            }
            else {
                resultModel.setCode(200);
                resultModel.setMsg("删除老师成功");
            }
        } catch (Exception e) {
            resultModel.setCode(401);
            resultModel.setMsg("删除老师失败");
            throw new RuntimeException(e);
        }finally {
            return resultModel;
        }

    }

    @Override
    public ResultModel updateById(TeacherDTO teacherDTO) {
        ResultModel resultModel = new ResultModel();
        //DTO--domain
        Teacher teacher = new Teacher();
        teacher.setTeacher_id(teacherDTO.getTeacher_id());
        teacher.setName(teacherDTO.getName());
        teacher.setPassword(teacherDTO.getPassword());
        teacher.setPhone(teacherDTO.getPhone());
        teacher.setMail(teacherDTO.getMail());

        int res = 0;
        try {
            res = teacherMapper.updateById(teacher);
            if(res==0){
                resultModel.setCode(401);
                resultModel.setMsg("更新老师失败");
            }
            else {
                resultModel.setCode(200);
                resultModel.setMsg("更新老师成功");
            }
        } catch (Exception e) {
            resultModel.setCode(401);
            resultModel.setMsg("更新老师失败");
            throw new RuntimeException(e);
        }finally {
            return resultModel;
        }
    }



    @Override
    public ResultModel login(String name, String password){
        ResultModel resultModel = new ResultModel();

        Map<String, String> map = new HashMap<String,String>();
        map.put("name", name);
        map.put("password", password);

        TeacherVO teacherVO = teacherMapper.login(map);

        if(teacherVO==null){
            resultModel.setCode(401);
            resultModel.setMsg("账号或密码错误");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("登录成功");
            resultModel.setData(name);
        }
        //resultModel.setData();
        return resultModel;
    }

    public ResultModel loginById(int teacher_id, String password) {
        Map<String, Teacher> map = new HashMap<String, Teacher>();
        Teacher teacher = new Teacher();
        teacher.setTeacher_id(teacher_id);
        teacher.setPassword(password);
        map.put("loginId", teacher);

        ResultModel resultModel = new ResultModel();
        TeacherVO teacherVO = teacherMapper.idLogin(map);

        if(teacherVO==null){
            resultModel.setCode(401);
            resultModel.setMsg("账号或密码错误");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("登录成功");
            resultModel.setData(teacher_id);
        }
        //resultModel.setData();
        return resultModel;
    }
}
