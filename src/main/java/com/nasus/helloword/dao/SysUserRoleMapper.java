package com.nasus.helloword.dao;

import com.nasus.helloword.form.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper {

    List<SysUserRole> listByUserId(Integer userId);
}
