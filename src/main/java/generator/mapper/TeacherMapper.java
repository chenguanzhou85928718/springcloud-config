package generator.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.Teacher;
import com.example.gradledemo.domain.TeacherCollegeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
* @Entity com.example.gradle_demo.domain.Teacher
*/
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    //利用select注解，实现teacher表关联college表查询
    @Select("select te.*,coll.collegeName as collegename from teacher te left join college coll on te.collegeID = coll.collegeID "
            + " ${ew.customSqlSegment}")
    //List<StudentCollegeVO> getStudentAndCollege(Page<StudentCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<StudentCollegeVO> queryWrapper);
    IPage<TeacherCollegeVO> getTeacherAndCollege(Page<TeacherCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<TeacherCollegeVO> queryWrapper);


}
