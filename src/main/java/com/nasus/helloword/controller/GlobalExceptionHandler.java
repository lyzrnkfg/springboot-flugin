package com.nasus.helloword.controller;

import com.nasus.helloword.exception.UserNotExistException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //https://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes,  Model model) {
        redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
        return "redirect:/student";
    }

    /*@ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e){
        System.out.println("未知异常！原因是:"+e);
        return e.getMessage();
    }*/

    // 浏览器和客户段返回的都是json
    /*@ResponseBody
    @ExceptionHandler(value = UserNotExistException.class)
    public Map<String, Object> handleException(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }*/

    @ExceptionHandler(value = UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        // 设置状态码 不设置的话返回200 200正常返回
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user");
        map.put("message", e.getMessage());
        //把数据放到request域中
        request.setAttribute("ext", map);
        // 转发到/error
        return "forward:/error";
    }


}
