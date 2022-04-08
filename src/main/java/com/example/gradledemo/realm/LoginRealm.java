package com.example.gradledemo.realm;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.gradledemo.domain.Role;
import com.example.gradledemo.domain.Userlogin;
import com.example.gradledemo.service.RoleService;
import com.example.gradledemo.service.UserloginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserloginService userloginService;

    @Autowired
    private RoleService roleService;


    /**
     * 提供用户信息返回权限信息，从数据库获取该用户的权限和角色信息
     * 当调用权限验证时，就会调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            //查询用户
            QueryWrapper<Userlogin> userloginQueryWrapper = new QueryWrapper<>();
            userloginQueryWrapper.eq("userName", username);
            Userlogin userlogin = userloginService.getOne(userloginQueryWrapper);
            //获取角色对象
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("roleID", userlogin.getRole());
            role = roleService.getOne(roleQueryWrapper);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRolename());
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 在这个方法中，提供账户信息返回认证信息
     * login时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //2.从UsernamePasswordToken中获取username
        String username = userToken.getUsername();

        //3.从数据库中查询Username对应的用户记录
         Userlogin userlogin = null;
        try {
            //查询用户
            QueryWrapper<Userlogin> userloginQueryWrapper = new QueryWrapper<>();
            userloginQueryWrapper.eq("userName", username);
            //System.out.println(userloginService.count(userloginQueryWrapper));
            userlogin = userloginService.getOne(userloginQueryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //4.若用户不行存在，可以抛出UnknownAccountException
        if(userlogin==null){
            throw new UnknownAccountException("用户不存在");
        }
        /*
        //5.若用户被锁定，可以抛出LockedAccountException
        if(userlogin.isLocked()){
            throw new LockedAccountException("用户被锁定");
        }
        */
        //6.根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
        //以下信息是从数据库中获取的
        //1)principal：认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对象
        Object principal = userlogin.getUsername();
        //2)credentials：密码
        Object credentials = userlogin.getPassword();
        //3)realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);
        return info;
    }

}
