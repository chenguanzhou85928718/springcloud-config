package generator.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gradledemo.domain.SelectedCourseStudentMarkVO;
import com.example.gradledemo.domain.Selectedcourse;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
* @Entity com.example.gradle_demo.domain.Selectedcourse
*/

//mapper需要继承MppBaseMapper，使mybatisplus支持联合主键的增删改查
@Mapper
public interface SelectedcourseMapper extends MppBaseMapper<Selectedcourse> {
    //利用select注解，实现selectedcourse表 关联course表、student表 查询
    //注意courseName "引号前面的空格
    @Select("select *, st.userName as studentName,co.courseName as courseName " +
            "from selectedcourse  " +
            "left join course co  on co.courseID = selectedcourse.courseID " +
            "left join student st on st.userID  = selectedcourse.studentID " +
            "${ew.customSqlSegment} ")

    //List<StudentCollegeVO> getStudentAndCollege(Page<StudentCollegeVO> page, @Param(Constants.WRAPPER) QueryWrapper<StudentCollegeVO> queryWrapper);
    IPage<SelectedCourseStudentMarkVO> getSelectedCourseMark(Page<SelectedCourseStudentMarkVO> page, @Param(Constants.WRAPPER) QueryWrapper<SelectedCourseStudentMarkVO> queryWrapper);


}
