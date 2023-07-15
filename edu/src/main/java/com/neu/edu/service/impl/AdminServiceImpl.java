package com.neu.edu.service.impl;

import com.neu.edu.mapper.AdminMapper;
import com.neu.edu.service.AdminService;
import com.neu.edu.utils.ResultModel;
import com.neu.edu.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public ResultModel login(String t_admin, String password){
        ResultModel resultModel = new ResultModel();

        Map<String, String> map = new HashMap<String,String>();
        map.put("t_admin", t_admin);
        map.put("password", password);

        AdminVO adminVO = adminMapper.login(map);


        if(adminVO==null){
            resultModel.setCode(401);
            resultModel.setMsg("账号或密码错误");
        }else {
            resultModel.setCode(200);
            resultModel.setMsg("登录成功");
            resultModel.setData(t_admin);
        }
        return resultModel;
    }
}
