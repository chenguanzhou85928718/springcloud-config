package com.example.gradledemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gradledemo.domain.College;
import com.example.gradledemo.service.CollegeService;
import com.example.gradledemo.mapper.CollegeMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
implements CollegeService{

}
