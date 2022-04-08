package com.example.gradledemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.SelectedCourseStudentMarkVO;
import com.example.gradledemo.domain.Selectedcourse;
import com.example.gradledemo.mapper.SelectedcourseMapper;
import com.example.gradledemo.service.CourseService;
import com.example.gradledemo.service.SelectedcourseService;
import com.example.gradledemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController //数据按json格式返回
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SelectedcourseService selectedcourseService;

    @Autowired(required = false)
    private SelectedcourseMapper selectedcourseMapper;


    //学生选的课
    //http://localhost:8087/student/showStudentCourse?pageNum=1&pageRow=6&student=10001
    @PostMapping("/showStudentCourse")
    @CrossOrigin
    public IPage<SelectedCourseStudentMarkVO> showStudentCourse( int pageNum, int pageRow, int student) {

        Page<SelectedCourseStudentMarkVO> page = new Page<>(pageNum, pageRow);
        QueryWrapper<SelectedCourseStudentMarkVO> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("courseID");//排序
        queryWrapper.eq("studentID", student);//只输出该学生的课
        //利用在SelectedcourseMapper中增加select注解关联course、student表，添加的getSelectedCourseMark方法
        IPage<SelectedCourseStudentMarkVO> selectedCourseStudentMarkVO = selectedcourseMapper.getSelectedCourseMark(page, queryWrapper);
        return selectedCourseStudentMarkVO;
    }


    //选课
    /*
    http://localhost:8087/student/addSelectedCourse
    {
        "courseid":"4",
        "studentid":10006,
        "mark":60
    }
    */
    @PostMapping("/addSelectedCourse")
    @CrossOrigin
    public boolean addSelectedCourse(@RequestBody Selectedcourse selectedCourse) {
        return selectedcourseService.saveOrUpdate(selectedCourse);
    }


    // 退课
    @PostMapping("/outSelectedCourse")
    @CrossOrigin
    public boolean outSelectedCourse(@RequestBody Selectedcourse selectedCourse){
        //逻辑删除，其实是更新 is_delete  0→1
        String is_delete = selectedCourse.getIsDelete();//0表示上架，1表示下架
        if(is_delete.equals("0")){
            is_delete="1";
        }else {
            is_delete="0";
        }
        Selectedcourse selectedCourse1 = new Selectedcourse();
        selectedCourse1.setCourseid(selectedCourse.getCourseid());
        selectedCourse1.setIsDelete(is_delete);//0表示上架，1表示下架
        return selectedcourseService.saveOrUpdate(selectedCourse1);
    }



    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "/student/passwordRest";
    }


}
