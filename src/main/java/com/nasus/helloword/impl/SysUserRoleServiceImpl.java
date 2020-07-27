package com.nasus.helloword.impl;

import com.nasus.helloword.dao.SysUserRoleMapper;
import com.nasus.helloword.form.SysUserRole;
import com.nasus.helloword.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }

}
