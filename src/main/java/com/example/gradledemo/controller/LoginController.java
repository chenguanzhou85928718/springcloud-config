package com.example.gradledemo.controller;

import com.example.gradledemo.domain.Userlogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 包含Web端以及微信小程序端登录
 */
@RestController
public class LoginController {

    //登录跳转
    //@RequestMapping(value = "/login", method = {RequestMethod.GET})
    @PostMapping("/loginUI")
    @CrossOrigin
    public String loginUI() throws Exception {
        return "/login";
    }

    //@RequestMapping(value="/logout",method = {RequestMethod.GET})
    @PostMapping("/logout")
    @CrossOrigin
    public String logout() throws Exception{
        return "/login";
    }


    @PostMapping("/wxlogin")
    //@GetMapping("/wxlogin")
    @CrossOrigin
    @ResponseBody
    public Map<String,String>  wxlogin(@RequestBody Userlogin userlogin) throws Exception{
        Map<String,String> map = new HashMap<String, String>();
        //Shiro实现登录
        // Map<String,String> map = new HashMap<String, String>();
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(),
                userlogin.getPassword());
        //Subject：项目，通过Shiro保护的项目一个抽象概念
        //通过令牌（token）与项目（subject）的登陆（login）关系，Shiro保证了项目整体的安全
        //获取Subject单例对象
        Subject subject = SecurityUtils.getSubject();
        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        //登录
        subject.login(token);

        if (subject.hasRole("admin")) {
            map.put("role","admin");
            map.put("username",userlogin.getUsername());
        } else if (subject.hasRole("teacher")) {
            map.put("role","teacher");
            map.put("username",userlogin.getUsername());
        } else if (subject.hasRole("student")) {
            map.put("role","student");
            map.put("username",userlogin.getUsername());
        }
        return map;

    }


    //登录表单处理
    //@RequestMapping(value = "/login", method = {RequestMethod.POST})
    @PostMapping("/login")
    @CrossOrigin
    public String login(Userlogin userlogin) throws Exception {

        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getUsername(),
                userlogin.getPassword());
        Subject subject = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        //登录
        subject.login(token);

        if (subject.hasRole("admin")) {
            return "redirect:/admin/showStudent";
        } else if (subject.hasRole("teacher")) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole("student")) {
            return "redirect:/student/showCourse";
        }

        return "/login";
    }

}
