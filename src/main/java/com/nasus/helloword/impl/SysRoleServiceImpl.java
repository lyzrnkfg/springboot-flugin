package com.nasus.helloword.impl;

import com.nasus.helloword.dao.SysRoleMapper;
import com.nasus.helloword.form.SysRole;
import com.nasus.helloword.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }

}
