package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gradledemo.domain.Teacher;
import com.example.gradledemo.mapper.TeacherMapper;
import com.example.gradledemo.service.TeacherService;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
implements TeacherService{

}
