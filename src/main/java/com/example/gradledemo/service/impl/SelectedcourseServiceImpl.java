package com.example.gradledemo.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.example.gradledemo.domain.Selectedcourse;
import com.example.gradledemo.service.SelectedcourseService;
import com.example.gradledemo.mapper.SelectedcourseMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class SelectedcourseServiceImpl extends MppServiceImpl<SelectedcourseMapper, Selectedcourse>
implements SelectedcourseService{

}
