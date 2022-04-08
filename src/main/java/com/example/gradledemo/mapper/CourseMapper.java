package com.example.gradledemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.Course;
import com.example.gradledemo.domain.CourseTeacherCollegeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
* @Entity com.example.gradle_demo.domain.Course
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    //利用select注解，实现course表 关联teacher表、college表 查询
    //注意collegename "等引号前面的空格
    @Select("select cou.*, te.userName as teachername ,coll.collegeName as collegename "
            +"from course cou "
            +"left join teacher te   on te.userID = cou.teacherID "
            +"left join college coll on coll.collegeID  = cou.collegeID "
            + " ${ew.customSqlSegment} ")
    //List<StudentCollegeVO> getStudentAndCollege(Page<StudentCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<StudentCollegeVO> queryWrapper);
    IPage<CourseTeacherCollegeVO> getCourseTeacherCollege(Page<CourseTeacherCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<CourseTeacherCollegeVO> queryWrapper);


}
