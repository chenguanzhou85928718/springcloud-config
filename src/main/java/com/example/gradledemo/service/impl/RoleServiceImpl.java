package com.example.gradledemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gradledemo.domain.Role;
import com.example.gradledemo.service.RoleService;
import com.example.gradledemo.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
implements RoleService{

}
