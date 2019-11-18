package com.code.common.jwt;

import com.alibaba.fastjson.JSONObject;
import com.code.modules.system.entity.SysUser;
import com.code.modules.system.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/18
 */
public class testController {
    @Autowired
    SysUserService userService;

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


    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
