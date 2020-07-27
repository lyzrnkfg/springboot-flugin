package com.nasus.helloword.bean;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleReslover implements LocaleResolver {

    // i18n 自己实现区域信息解析器
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // //获取请求参数中的语言
        String l = httpServletRequest.getParameter("l");
        // //没带区域信息参数就用系统默认的
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            //提交的参数是xx_XX （语言代码_国家代码）
            String[] s = l.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
