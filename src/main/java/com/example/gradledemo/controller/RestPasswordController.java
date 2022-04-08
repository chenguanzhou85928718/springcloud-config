package com.example.gradledemo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gradledemo.exception.CustomException;
import com.example.gradledemo.domain.Userlogin;
import com.example.gradledemo.mapper.UserloginMapper;
import com.example.gradledemo.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class RestPasswordController {

    //@Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;
    private UserloginMapper userloginMapper;

    // 本账户密码重置
    //@RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    @PostMapping("/passwordRest")
    @CrossOrigin
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //获取登录的存储对象
        String username = (String) subject.getPrincipal();
        //Userlogin userlogin = userloginService.findByName(username);
        QueryWrapper<Userlogin> queryWrapper = new QueryWrapper<>();
        //查询名字为 Tom 的一条记录
        queryWrapper.eq("name",username);
        Userlogin userlogin = userloginMapper.selectOne(queryWrapper);

        if (!oldPassword.equals(userlogin.getPassword())) {
            throw new CustomException("旧密码不正确");
        } else {
            userlogin.setPassword(password1);
            userloginService.saveOrUpdate(userlogin);
        }

        return "redirect:/logout";
    }

    //微信密码重置
    //@RequestMapping(value = "/wxpasswordRset",method = {RequestMethod.POST, RequestMethod.GET})
    //@ResponseBody
    @PostMapping("/wxpasswordRset")
    @CrossOrigin
    public Map<String,Object> wxpasswordRset(String username,String oldPassword,String password) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        //Userlogin userlogin = userloginService.findByName(username);
        QueryWrapper<Userlogin> queryWrapper = new QueryWrapper<>();
        //查询名字为 Tom 的一条记录
        queryWrapper.eq("name",username);
        Userlogin userlogin = userloginMapper.selectOne(queryWrapper);
        System.out.println(userlogin.getUsername());
        if (!oldPassword.equals(userlogin.getPassword())) {
            map.put("password","oldexception");
        } else {
            userlogin.setPassword(password);
            userloginService.saveOrUpdate(userlogin);
            map.put("password","true");
        }
        return map;
    }

}
