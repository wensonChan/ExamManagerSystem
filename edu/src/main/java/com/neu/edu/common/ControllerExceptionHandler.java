package com.neu.edu.common;

import com.neu.edu.utils.ResultModel;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/14 20:17
 */
@RestControllerAdvice(basePackages = "com.neu.edu.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultModel handler(Exception ex) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(500);
        resultModel.setMsg("出现了操作"+ex.getMessage() + "的失败");
        return resultModel;
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResultModel handler(BusinessException ex) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(500);
        resultModel.setMsg("操作"+ ex.getMsg() + "失败");
        return resultModel;
    }

}
