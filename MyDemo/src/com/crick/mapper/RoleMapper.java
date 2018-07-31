package com.crick.mapper;

import org.apache.ibatis.annotations.Select;

import com.crick.pojo.Role;

public interface RoleMapper {
	@Select("select id,role_name as roleName,note from t_role where id=#{id}")
	public Role getRole(Long id);

}
