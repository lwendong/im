package com.im.service;

import com.alibaba.fastjson.JSONObject;
import com.im.model.User;

public interface ImService {

    JSONObject login(User user);

    JSONObject regis(User user);

    JSONObject getFriends(String loginId);

    JSONObject getChat(String loginId);

    boolean initChat(String fromUserId,String toUserId);

    String sendCode(String toMailAccount);

    void addUserName(String userName,String email);
}
