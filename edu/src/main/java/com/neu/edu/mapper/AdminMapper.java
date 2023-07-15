package com.neu.edu.mapper;

import com.neu.edu.vo.AdminVO;

import java.util.Map;

public interface AdminMapper {

    AdminVO login(Map<String, String> map);
}
