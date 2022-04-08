package generator.service.impl;


import com.example.gradledemo.domain.Selectedcourse;
import com.example.gradledemo.mapper.SelectedcourseMapper;
import com.example.gradledemo.service.SelectedcourseService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class SelectedcourseServiceImpl extends MppServiceImpl<SelectedcourseMapper, Selectedcourse>
implements SelectedcourseService{

}
