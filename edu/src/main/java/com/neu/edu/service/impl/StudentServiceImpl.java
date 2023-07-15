package com.neu.edu.service.impl;

import com.neu.edu.domain.Student;
import com.neu.edu.dto.StudentDTO;
import com.neu.edu.mapper.StudentMapper;
import com.neu.edu.service.StudentService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentMapper studentMapper;

    @Override
    public ResultModel<List<StudentVO>> findAll(){
        ResultModel<List<StudentVO>> resultModel = new ResultModel<List<StudentVO>>();
        List<StudentVO> studentVOList = studentMapper.findAll();
        resultModel.setCode(200);
        resultModel.setMsg("查询学生成功");
        resultModel.setData(studentVOList);
        return resultModel;
    }

    @Override
    public ResultModel findById(int id){
        ResultModel resultModel = new ResultModel();
        StudentVO studentVO = studentMapper.findById(id);

        if(studentVO==null){
            resultModel.setCode(401);
            resultModel.setMsg("该学生不存在");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("查询学生成功");
            resultModel.setData(studentVO);
        }

        return resultModel;
    }


    @Override
    public ResultModel add(StudentDTO studentDTO) {
        ResultModel resultModel = new ResultModel();

        //DTO--domain
        Student student = new Student();
        student.setStudent_id(studentDTO.getStudent_id());
        student.setStu_name(studentDTO.getStu_name());
        System.out.println(studentDTO.getStu_name());
        //System.out.println("1");
        System.out.println(student.getStu_name());
        //System.out.println("2");
        student.setPassword(studentDTO.getPassword());
        student.setPhone(studentDTO.getPhone());
        student.setMail(studentDTO.getMail());

        if(studentMapper.add(student)==0){
            resultModel.setCode(401);
            resultModel.setMsg("添加学生失败");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("添加学生成功");
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteById(int id){

        ResultModel resultModel = new ResultModel();
        int res = 0;
        try {

            res = studentMapper.deleteById(id);
            if(res==0){
                resultModel.setCode(401);
                resultModel.setMsg("删除学生失败");
            }
            else {
                resultModel.setCode(200);
                resultModel.setMsg("删除学生成功");
            }
        } catch (Exception e) {
            resultModel.setCode(401);
            resultModel.setMsg("删除学生失败");
            throw new RuntimeException(e);
        }finally {
            return resultModel;
        }

    }

    @Override
    public ResultModel updateById(StudentDTO studentDTO) {
        ResultModel resultModel = new ResultModel();
        //DTO--domain
        Student student = new Student();
        student.setStudent_id(studentDTO.getStudent_id());
        student.setStu_name(studentDTO.getStu_name());
        student.setPassword(studentDTO.getPassword());
        student.setPhone(studentDTO.getPhone());
        student.setMail(studentDTO.getMail());


        int res = 0;
        try {

            res = studentMapper.updateById(student);
            if(res==0){
                resultModel.setCode(401);
                resultModel.setMsg("更新学生失败");
            }
            else {
                resultModel.setCode(200);
                resultModel.setMsg("更新学生成功");
            }
        } catch (Exception e) {
            resultModel.setCode(401);
            resultModel.setMsg("更新学生失败");
            throw new RuntimeException(e);
        }finally {
            return resultModel;
        }
    }

    @Override
    public ResultModel login(String stu_name, String password){
        ResultModel resultModel = new ResultModel();

        Map<String, String> map = new HashMap<String,String>();
        map.put("stu_name", stu_name);
        map.put("password", password);

        StudentVO studentVO = studentMapper.login(map);

        if(studentVO==null){
            resultModel.setCode(401);
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
            resultModel.setCode(401);
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
