package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Course;
import generator.service.CourseService;
import generator.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
implements CourseService{

}
