package com.neu.edu.service;

import com.neu.edu.utils.ResultModel;

public interface AdminService {

    ResultModel<String> login(String t_admin, String password);
}
