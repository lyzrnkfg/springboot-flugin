package com.nasus.helloword.service;

import com.nasus.helloword.form.SysUser;
import com.nasus.helloword.form.SysUserRole;

import java.util.List;


public interface SysUserRoleService {

    List<SysUserRole> listByUserId(Integer userId);

}
