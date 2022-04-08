package com.example.gradledemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.StudentCollegeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentCollegeMapper {
    //利用select注解，实现student表关联college表查询
    @Select("select stu.*,coll.collegeName as collegename from student stu left join college coll on stu.collegeID = coll.collegeID "
            + " ${ew.customSqlSegment}")

    //List<StudentCollegeVO> getStudentAndCollege(Page<StudentCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<StudentCollegeVO> queryWrapper);
    IPage<StudentCollegeVO> getStudentAndCollege(Page<StudentCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<StudentCollegeVO> queryWrapper);


}
