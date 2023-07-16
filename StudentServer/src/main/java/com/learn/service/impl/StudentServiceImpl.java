package com.learn.service.impl;

import com.learn.domain.Student;
import com.learn.dto.StudentDTO;
import com.learn.mapper.StudentMapper;
import com.learn.service.StudentService;
import com.learn.utils.ResultModel;
import com.learn.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    Student student;

    @Override
    public ResultModel<StudentVO> findAll(int id) {
        ResultModel<StudentVO> resultModel = new ResultModel<>();
        StudentVO studentVO = studentMapper.findAll(id);

        if(studentVO == null){
            resultModel.setCode(404);
            resultModel.setMsg("查询个人信息失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("查询个人信息成功");
            resultModel.setData(studentVO);
        }
        return resultModel;
    }

    @Override
    public ResultModel updateSome(StudentDTO studentDTO) {
        ResultModel resultModel = new ResultModel();
        student.setStudent_id(studentDTO.getStudent_id());
        student.setPassword(studentDTO.getPassword());
        student.setPhone(studentDTO.getPhone());
        student.setMail(studentDTO.getMail());
        int result = studentMapper.updateSome(student);
        if(result == 0){
            resultModel.setCode(404);
            resultModel.setMsg("修改个人信息失败");
        }else{
            resultModel.setCode(200);
            resultModel.setMsg("修改个人信息成功");
        }
        return resultModel;
    }
    @Override
    public ResultModel login(String stu_name, String password){
        ResultModel resultModel = new ResultModel();

        Map<String, String> map = new HashMap<String,String>();
        map.put("stu_name", stu_name);
        map.put("password", password);

        StudentVO studentVO = studentMapper.login(map);

        if(studentVO==null){
            resultModel.setCode(404);
            resultModel.setMsg("账号或密码错误");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("登录成功");
            resultModel.setData(stu_name);
        }

        return resultModel;
    }


    public ResultModel loginById(int student_id, String password) {
        Map<String, Student> map = new HashMap<String, Student>();
        Student student = new Student();
        student.setStudent_id(student_id);
        student.setPassword(password);
        map.put("loginId", student);

        ResultModel resultModel = new ResultModel();
        StudentVO studentVO = studentMapper.idLogin(map);

        if(studentVO==null){
            resultModel.setCode(404);
            resultModel.setMsg("账号或密码错误");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("登录成功");
            resultModel.setData(student_id);
        }
        //resultModel.setData();
        return resultModel;
    }

}
