package com.example.gradledemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.*;
import com.example.gradledemo.mapper.CourseMapper;
import com.example.gradledemo.mapper.SelectedcourseMapper;
import com.example.gradledemo.service.CourseService;
import com.example.gradledemo.service.SelectedcourseService;
import com.example.gradledemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController//数据按json格式返回
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired(required = false)
    private CourseMapper courseMapper;
    @Autowired
    private CourseService courseService;

    @Autowired(required = false)
    private SelectedcourseMapper selectedcourseMapper;
    @Autowired
    private SelectedcourseService selectedcourseService;


    // 显示老师的课程
    //http://localhost:8087/teacher/showTeacherCourse?pageNum=1&pageRow=5&userlogin=1001
    @PostMapping("/showTeacherCourse")
    @CrossOrigin
    public IPage<CourseTeacherCollegeVO> showTeacherCourse(int pageNum, int pageRow, int userlogin){

        Page<CourseTeacherCollegeVO> page = new Page<>(pageNum,pageRow);
        QueryWrapper<CourseTeacherCollegeVO> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("courseID");//排序
        queryWrapper.eq("teacherID",userlogin);//只输出该老师的课
        //利用在courseMapper中增加select注解关联teacher、college表，添加的getCourseTeacherCollege方法
        IPage<CourseTeacherCollegeVO> courseTeacherCollegeVO = courseMapper.getCourseTeacherCollege(page, queryWrapper);
        return courseTeacherCollegeVO;
    }


    // 根据课程显示成绩
    //http://localhost:8087/teacher/gradeCourse?pageNum=1&pageRow=5&course=1
    @PostMapping("/gradeCourse")
    @CrossOrigin
    public IPage<SelectedCourseStudentMarkVO> gradeCourse(int pageNum, int pageRow, int course){

        Page<SelectedCourseStudentMarkVO> page = new Page<>(pageNum,pageRow);
        QueryWrapper<SelectedCourseStudentMarkVO> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("mark");//排序
        queryWrapper.eq("selectedcourse.courseID",course);//只输出该课程的分,selectedcourse是名，见SelectedcourseMapper
        //利用在SelectedcourseMapper中增加select注解，实现selectedcourse表关联course表、student表添加的getSelectedCourseMark方法
        IPage<SelectedCourseStudentMarkVO> selectedCourseMarkVO = selectedcourseMapper.getSelectedCourseMark(page, queryWrapper);
        return selectedCourseMarkVO;
    }

    // 打分
    @PostMapping("/addMark")
    @CrossOrigin
    public boolean addMark(@RequestBody Selectedcourse selectedcourse){
        return selectedcourseService.saveOrUpdate(selectedcourse);
    }


    // 更新打分
    @PostMapping("/updateMark")
    @CrossOrigin
    public boolean updateMark(@RequestBody Selectedcourse selectedcourse){
        Selectedcourse selectedcourse1 = new Selectedcourse();
        selectedcourse1.setMark(selectedcourse.getMark());
        //course1.setIs_delete("1");//删除操作单独操作
        return selectedcourseService.saveOrUpdate(selectedcourse1);
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "/teacher/passwordRest";
    }

}
