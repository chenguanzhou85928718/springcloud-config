package generator.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gradledemo.domain.Userlogin;
import com.example.gradledemo.mapper.UserloginMapper;
import com.example.gradledemo.service.UserloginService;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class UserloginServiceImpl extends ServiceImpl<UserloginMapper, Userlogin>
implements UserloginService{

}
