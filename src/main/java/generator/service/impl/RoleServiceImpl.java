package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Role;
import generator.service.RoleService;
import generator.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
implements RoleService{

}
