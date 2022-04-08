package com.example.gradledemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.*;
import com.example.gradledemo.mapper.CourseMapper;
import com.example.gradledemo.mapper.StudentCollegeMapper;
import com.example.gradledemo.mapper.StudentMapper;
import com.example.gradledemo.mapper.TeacherMapper;
import com.example.gradledemo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController //数据按json格式返回
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired(required = false)
    private StudentMapper studentMapper;

    @Autowired(required = false)
    private StudentCollegeMapper studentCollegeMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired(required = false)
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseService courseService;

    @Autowired(required = false)
    private CourseMapper courseMapper;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserloginService userloginService;

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<College操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @PostMapping("/showAllCollege")
    @CrossOrigin
    public IPage<College> getCollegeAll(int pageNum, int pageRow) {
        Page<College> page = new Page<>(pageNum, pageRow);
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("collegename");//排序
        IPage<College> iPage = collegeService.page(page, queryWrapper);
        return iPage;

    }

    @PostMapping("/showCollege")
    @CrossOrigin
    public IPage<College> getCollegeList(int pageNum, int pageRow) {
        Page<College> page = new Page<>(pageNum, pageRow);
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("collegeName");//排序
        queryWrapper.eq("is_delete", "0");//只输出在用
        return collegeService.page(page, queryWrapper);
    }

    /*  postman测试，json格式发送数据
    http://localhost:8087/admin/addCollege
       {
         "collegeid":7,
         "collegename":"android开发"
        }
     */
    @PostMapping("/addCollege")
    @CrossOrigin
    public boolean addCollege(@RequestBody College college) {
        return collegeService.saveOrUpdate(college);
    }

    @PostMapping("/updateCollege")
    @CrossOrigin
    public boolean updateCollege(@RequestBody College college) {
        College college1 = new College();
        college1.setCollegeid(college.getCollegeid());
        college1.setCollegename(college.getCollegename());
        //vehicle.setIs_delete("1");//删除操作单独操作
        return collegeService.saveOrUpdate(college1);
    }

    @PostMapping("/deleteCollege")
    @CrossOrigin
    public boolean deleteCollege(@RequestBody College college) {
        //逻辑删除，其实是更新 is_delete  0→1
        String is_delete = college.getIsDelete();//0表示上架，1表示下架
        if (is_delete.equals("0")) {
            is_delete = "1";
        } else {
            is_delete = "0";
        }
        College college1 = new College();
        college1.setCollegeid(college.getCollegeid());
        college1.setIsDelete(is_delete);//0表示上架，1表示下架
        return collegeService.saveOrUpdate(college1);
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<学生操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    @PostMapping("/showStudent")
    @CrossOrigin
    //http://localhost:8087/admin/showStudent?pageNum=1&pageRow=5
    public IPage<StudentCollegeVO> showStudent(int pageNum, int pageRow) {
        //System.out.println(pageNum);
        Page<StudentCollegeVO> page = new Page<>(pageNum, pageRow);
        QueryWrapper<StudentCollegeVO> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("userID");//排序
        //利用在StudentMapper中增加select注解,关联student表与college表，添加的getStudentAndCollege方法
        IPage<StudentCollegeVO> studentCollege = studentCollegeMapper.getStudentAndCollege(page, queryWrapper);
        return studentCollege;
    }

    /*
        http://localhost:8087/admin/addStudent
        {
            "username":"高磊",
            "sex":"男",
            "birthyear":"1986-05-09",
            "grade":"2016-05-09",
            "collegeid":3
        }
     */
    @PostMapping("/addStudent")
    //@GetMapping("/addStudent")
    @CrossOrigin
    //@RequestMapping(value = "/addStudent", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> addStudent(@RequestBody Student student) {
        Map<String, Object> map = new HashMap<String, Object>();
        studentService.saveOrUpdate(student);
        map.put("addstudent","true");
        return map;
    }

    @PostMapping("/updateStudent")
    @CrossOrigin
    public boolean updateStudent(@RequestBody Student student) {
        Student student1 = new Student();
        student1.setUserid(student.getUserid());
        student1.setUsername(student.getUsername());
        student1.setSex(student.getSex());
        student1.setGrade(student.getGrade());
        student1.setCollegeid(student.getCollegeid());
        student1.setBirthyear(student.getBirthyear());
        //student1.setIs_delete("1");//删除操作单独操作
        return studentService.saveOrUpdate(student1);
    }

    @PostMapping("/deleteStudent")
    @CrossOrigin
    public boolean deleteStudent(@RequestBody Student student) {
        //逻辑删除，其实是更新 is_delete  0→1
        String is_delete = student.getIsDelete();//0表示上架，1表示下架
        if (is_delete.equals("0")) {
            is_delete = "1";
        } else {
            is_delete = "0";
        }
        Student student1 = new Student();
        student1.setUserid(student.getUserid());
        student1.setIsDelete(is_delete);//0表示上架，1表示下架
        return studentService.saveOrUpdate(student1);
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<教师操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    @PostMapping("/showTeacher")
    //http://localhost:8087/admin/showTeacher?pageNum=1&pageRow=7
    public IPage<TeacherCollegeVO> showTeacher(int pageNum, int pageRow) {

        Page<TeacherCollegeVO> page = new Page<>(pageNum, pageRow);
        QueryWrapper<TeacherCollegeVO> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("userID");//排序
        //利用在StudentMapper中增加select注解关联teacher表与college表，添加的getTeacherAndCollege方法
        IPage<TeacherCollegeVO> teacherCollege = teacherMapper.getTeacherAndCollege(page, queryWrapper);
        return teacherCollege;
    }

    /*
        http://localhost:8087/admin/addTeacher
        {
            "username":"高磊",
            "sex":"男",
            "birthyear":"1986-05-09",
            "grade":"2016-05-09",
            "title":"高级教师",
            "degree":"博士",
            "collegeid":3
        }
      */
    @PostMapping("/addTeacher")
    @CrossOrigin
    public boolean addTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdate(teacher);
    }

    @PostMapping("/updateTeacher")
    @CrossOrigin
    public boolean updateTeacher(@RequestBody Teacher teacher) {
        Teacher teacher1 = new Teacher();
        teacher1.setUserid(teacher.getUserid());
        teacher1.setUsername(teacher.getUsername());
        teacher1.setSex(teacher.getSex());
        teacher1.setGrade(teacher.getGrade());
        teacher1.setDegree(teacher.getDegree());
        teacher1.setTitle(teacher.getTitle());
        teacher1.setCollegeid(teacher.getCollegeid());
        teacher1.setBirthyear(teacher.getBirthyear());
        //student1.setIs_delete("1");//删除操作单独操作
        return teacherService.saveOrUpdate(teacher1);
    }

    @PostMapping("/deleteTeacher")
    @CrossOrigin
    public boolean deleteTeacher(@RequestBody Teacher teacher) {
        //逻辑删除，其实是更新 is_delete  0→1
        String is_delete = teacher.getIsDelete();//0表示上架，1表示下架
        if (is_delete.equals("0")) {
            is_delete = "1";
        } else {
            is_delete = "0";
        }
        Teacher teacher1 = new Teacher();
        teacher1.setUserid(teacher.getUserid());
        teacher1.setIsDelete(is_delete);//0表示上架，1表示下架
        return teacherService.saveOrUpdate(teacher1);
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<课程操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    @PostMapping("/showCourse")
    @CrossOrigin
    //http://localhost:8087/admin/showCourse?pageNum=1&pageRow=5
    public IPage<CourseTeacherCollegeVO> showCourse(int pageNum, int pageRow) {

        Page<CourseTeacherCollegeVO> page = new Page<>(pageNum, pageRow);
        QueryWrapper<CourseTeacherCollegeVO> queryWrapper = new QueryWrapper<>();//查询mapper
        queryWrapper.orderByDesc("userID");//排序
        //利用在StudentMapper中增加select注解关联course、teacher表与college表，添加的getCourseTeacherCollege方法
        IPage<CourseTeacherCollegeVO> courseTeacherCollegeVO = courseMapper.getCourseTeacherCollege(page, queryWrapper);
        return courseTeacherCollegeVO;
    }

    /*
    http://localhost:8087/admin/addCourse
    {
        "coursename":"gradle编程",
            "teacherid":1002,
            "coursetime":"周三",
            "classroom":"石C4-209",
            "courseweek":12,
            "coursetype":"普通班",
            "collegeid":3,
            "score":3
    }
    */
    @PostMapping("/addCourse")
    @CrossOrigin
    public boolean addCourse(@RequestBody Course course) {
        return courseService.saveOrUpdate(course);
    }

    @PostMapping("/updateCourse")
    @CrossOrigin
    public boolean updateCourse(@RequestBody Course course) {
        Course course1 = new Course();
        course1.setCourseid(course.getCourseid());
        course1.setCoursename(course.getCoursename());
        course1.setClassroom(course.getClassroom());
        course1.setCoursetime(course.getCoursetime());
        course1.setCoursetype(course.getCoursetype());
        course1.setCourseweek(course.getCourseweek());
        course1.setCollegeid(course.getCollegeid());
        course1.setTeacherid(course.getTeacherid());
        course1.setScore(course.getScore());
        //course1.setIs_delete("1");//删除操作单独操作
        return courseService.saveOrUpdate(course1);
    }

    @PostMapping("/deleteCourse")
    @CrossOrigin
    public boolean deleteCourse(@RequestBody Course course) {
        //逻辑删除，其实是更新 is_delete  0→1
        String is_delete = course.getIsDelete();//0表示上架，1表示下架
        if (is_delete.equals("0")) {
            is_delete = "1";
        } else {
            is_delete = "0";
        }
        Course course1 = new Course();
        course1.setCollegeid(course.getCourseid());
        course1.setIsDelete(is_delete);//0表示上架，1表示下架
        return courseService.saveOrUpdate(course1);
    }

    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<其他操作>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


}
