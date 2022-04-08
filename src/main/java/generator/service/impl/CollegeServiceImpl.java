package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.College;
import generator.service.CollegeService;
import generator.mapper.CollegeMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
implements CollegeService{

}
