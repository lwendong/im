package com.im.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.im.Utils.*;
import com.im.constant.GyConstant;
import com.im.model.MailInfo;
import com.im.model.User;
import com.im.service.ImService;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImServiceImpl implements ImService {

    @Override
    public JSONObject login(User user) {
        JSONObject result = new JSONObject();
        User resulUser = UserUtils.userExistByEmailAndPassword(user);
        if (resulUser != null) {
            result.put("success",true);
            result.put("msg","登录成功");
            result.put("userName",resulUser.getLoginName());
            result.put("email",user.getEmail());
            result.put("loginId",resulUser.getLoginId());
        }else {
            result.put("success",false);
            result.put("msg","账号或密码错误");
        }
        addSessAndCook(resulUser);
        return result;
    }

    @Override
    public JSONObject regis(User user) {
        JSONObject result = new JSONObject();
        User iuser = UserUtils.getUserByEmail(user.getEmail());
        if (iuser != null) {
            result.put("success",false);
            result.put("msg","邮箱存在");
            result.put("userName",iuser.getLoginName());
            return result;
        }
        String id = ImUtils.uuid();
        String loginId = ImUtils.uuid();
        user.setId(id);
        user.setLoginId(loginId);
        UserUtils.addUser(user);
        addSessAndCook(user);
        result.put("success",true);
        result.put("msg","注册成功");
        result.put("userName",user.getLoginName());
        result.put("email",user.getEmail());
        result.put("loginId",loginId);
        return result;
    }

    @Override
    public JSONObject getFriends(String loginId) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject data = new JSONObject();
        Set<String> py = new HashSet<>();
        String pySzmZc = null;
        List<Map<String,Object>> friends = UserUtils.getFriends(loginId);
        for (Map<String,Object> friend:friends) {
            String pySzm = ObjectUtils.toString(friend.get("pySzm"));
            py.add(pySzm);
//            if(!StringUtils.equals(pySzmZc,pySzm)){
//                if(!array.isEmpty()){
//                    data.put(pySzmZc,array.clone());
//                    array.clear();
//                }
//                pySzmZc = pySzm;
//            }
            JSONObject json = new JSONObject();
            json.put("friendId",friend.get("friendId"));
            json.put("userName",friend.get("name"));
            json.put("pySzm",friend.get("pySzm"));
            json.put("nickName",friend.get("C_NickName"));
            json.put("signTure",friend.get("C_SignaTure"));
            array.add(json);

        }
//        data.put(pySzmZc,array.clone());
        result.put("data",array);
        result.put("py",py);
        return result;
    }

    @Override
    public JSONObject getChat(String loginId) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        List<Map<String,String>> chatList = UserUtils.getChatList(loginId);
        for(Map<String,String> chat : chatList){
            Set<Map.Entry<String,String>> c = chat.entrySet();
            for(Map.Entry<String,String> cc : c){

            }

        }
        return result;
    }

    @Override
    public boolean initChat(String fromUserId, String toUserId) {
        String id = ImUtils.uuid();
        int num = UserUtils.getChat(fromUserId,toUserId);
        if(num == 0){
            UserUtils.initChat(id, GyConstant.CHAT_STATE_INIT,GyConstant.CHAT_TYPE_INIT,fromUserId,toUserId);
            return true;
        }
        return false;
    }

    @Override
    public String sendCode(String toMailAccount) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(random.nextInt(10)).append(random.nextInt(10))
                .append(random.nextInt(10)).append(random.nextInt(10))
                .append(random.nextInt(10)).append(random.nextInt(10));
        MailInfo mailInfo = new MailInfo();
        mailInfo.setToMailAccount(toMailAccount);
        mailInfo.setTitle(MailUtils.TITLE);
        mailInfo.setContent(MailUtils.CONTENT_PREFIX+"验证码:"+stringBuilder+" 您正在使用注册功能");
        MailUtils.sendMail(mailInfo);
        return stringBuilder.toString();
    }

    @Override
    public void addUserName(String userName, String email) {
        UserUtils.addUserNameByEmail(userName,email);
    }

    private void addSessAndCook(User user){
        ImWebUtils.setSessionAttribute("userName",user.getLoginName());
        ImWebUtils.setSessionAttribute("loginId",user.getLoginId());
        ImWebUtils.setSessionAttribute("email",user.getEmail());
        CookieUtils.setCookie("userName",user.getLoginName(),0);
        CookieUtils.setCookie("password",user.getPassword(),0);
        CookieUtils.setCookie("email",user.getEmail(),0);
    }
}
