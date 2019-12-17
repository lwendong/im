package com.im.controller;

import com.alibaba.fastjson.JSONObject;
import com.im.Utils.ImWebUtils;
import com.im.Utils.UserUtils;
import com.im.model.User;
import com.im.service.ImService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImController {

    @Autowired
    private ImService imService;

    @RequestMapping("/index")
    public String toIndex(){
        ImWebUtils.removeSessionAttribute("userName");
        ImWebUtils.removeSessionAttribute("loginId");
        return "index";
    }

    @RequestMapping("/home")
    public String toHome(){
        return "home";
    }

    @PostMapping("/regis")
    @ResponseBody
    public JSONObject regis(User user){
        JSONObject result = imService.regis(user);
        return result;
    }

    @PostMapping("/login")
    @ResponseBody
    public JSONObject login(User user){
        JSONObject result = imService.login(user);
        return result;
    }

    @PostMapping("/getFriends")
    @ResponseBody
    public JSONObject getFriends(String loginId){
        JSONObject result = imService.getFriends(loginId);
        return result;
    }

    @PostMapping("/getChat")
    @ResponseBody
    public JSONObject getChat(String loginId){
        JSONObject result = imService.getChat(loginId);
        return result;
    }

    @PostMapping("/initChat")
    @ResponseBody
    public boolean initChat(String fromUserId,String toUserId){
        return imService.initChat(fromUserId,toUserId);
    }

    @PostMapping("/sendCode")
    @ResponseBody
    public String sendCode(String toMailAccount){
        String code = imService.sendCode(toMailAccount);
        return code;
    }

    @RequestMapping("/fpa")
    public String fpa(){
        return "fpassword";
    }

    @PostMapping("/addUserName")
    @ResponseBody
    public void addUseName(String userName,String email){
        imService.addUserName(userName,email);
    }

    @GetMapping("/test")
    public void test(String loginName){
        User user = UserUtils.getUserByLoginName(loginName);
        int a = 0;
    }

}

