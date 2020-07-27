package com.nasus.helloword.dao;

import com.nasus.helloword.form.SysRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMapper {

    @Select("SELECT * FROM sys_role WHERE id = #{id}")
    SysRole selectById(Integer id);
}
