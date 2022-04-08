package com.example.gradledemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gradledemo.domain.Role;
import org.apache.ibatis.annotations.Mapper;


/**
* @Entity com.example.gradle_demo.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


}
