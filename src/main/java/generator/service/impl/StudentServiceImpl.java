package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gradledemo.domain.Student;
import com.example.gradledemo.mapper.StudentMapper;
import com.example.gradledemo.service.StudentService;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
implements StudentService{

}
