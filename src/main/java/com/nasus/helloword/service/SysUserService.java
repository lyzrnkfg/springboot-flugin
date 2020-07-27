package com.nasus.helloword.service;

import com.nasus.helloword.form.SysUser;


public interface SysUserService {

    SysUser selectById(Integer id);

    SysUser selectByName(String name);

}
