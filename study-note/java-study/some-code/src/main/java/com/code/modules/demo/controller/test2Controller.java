package com.code.modules.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.code.common.jwt.annotation.TakenAuth;
import com.code.modules.system.entity.SysUser;
import com.code.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/18
 */
@RestController
public class test2Controller {
    @Autowired
    SysUserService userService;

    @PostMapping("/save")
    public Object save(@RequestBody SysUser user){
        return userService.save(user);
    }
    //登录
    @PostMapping("/login")
    public Object login(@RequestBody SysUser user){
        JSONObject jsonObject=new JSONObject();
        SysUser userForBase=userService.findByUsername(user.getName());
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = SysUser.createToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }


    @TakenAuth
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
