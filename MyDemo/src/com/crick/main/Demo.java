package com.crick.main;

import org.apache.ibatis.session.SqlSession;

import com.crick.mapper.RoleMapper;
import com.crick.pojo.Role;
import com.crick.utils.SqlSessionFactoryUtils;

public class Demo {
	public static void main(String[] args) {
		SqlSession session=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		//SqlSession·¢ËÍsql
		Role role=session.selectOne("com.crick.mapper.RoleMapper.getRole", 1L);
		System.out.println(role.getNote());
		//Mapper·¢ËÍsql
		role=session.getMapper(RoleMapper.class).getRole(1L);
		System.out.println(role.getNote());
	}
}
