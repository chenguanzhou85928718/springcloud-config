package com.example.gradledemo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gradledemo.domain.Course;
import com.example.gradledemo.service.CourseService;
import com.example.gradledemo.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
implements CourseService{

}
