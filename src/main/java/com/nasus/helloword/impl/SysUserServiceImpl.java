package com.nasus.helloword.impl;

import com.nasus.helloword.dao.SysUserMapper;
import com.nasus.helloword.form.SysUser;
import com.nasus.helloword.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }

}
