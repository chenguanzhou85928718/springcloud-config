package com.example.gradledemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gradledemo.domain.College;
import org.apache.ibatis.annotations.Mapper;


/**
* @Entity com.example.gradle_demo.domain.College
*/
@Mapper
public interface CollegeMapper extends BaseMapper<College> {


}
