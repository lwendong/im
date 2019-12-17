package com.im.Utils;

import com.im.dao.UserDao;
import com.im.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class UserUtils {

    @Autowired
    private UserDao userDao;

    private static UserDao staticUserDao;

    @PostConstruct
    public void init(){
        staticUserDao = userDao;
    }

    /**
     * 获取所有的用户信息
     */
    public static List<User> getAllUser() {
        return staticUserDao.getAllUesr();
    }

    /**
     * 通过loginName判断用户是否存在
     */
    public static boolean userExistByLoginName(String loginName) {
        int userNum = staticUserDao.userExistByLoginName(loginName);
        if(userNum > 0){
            return true;
        }
        return false;
    }

    /**
     * 通过email判断用户是否存在
     */
    public static boolean userExistByEmail(String email) {
        int userNum = staticUserDao.userExistByEmail(email);
        if(userNum > 0){
            return true;
        }
        return false;
    }

    /**
     * 通过loginName获取用户
     */
    public static User getUserByLoginName(String loginName) {
        return staticUserDao.getUserByName(loginName);
    }
    /**
     * 通过loginName获取用户
     */
    public static User getUserByEmail(String email) {
        return staticUserDao.getUserByEmail(email);
    }

    /**
     * 添加用户
     */
    public static void addUser(User user){
        staticUserDao.addUser(user);
    }

    /**
     * 通过登录名称和密码判断用户是否存在
     */
    public static User userExistByLoginNameAndPassword(User user){
       User resulutUser = staticUserDao.userExistByLoginNameAndPassword(user.getLoginName(),user.getPassword());
       return resulutUser == null ? null : resulutUser;
    }

    /**
     * 通过邮箱和密码判断用户是否存在
     */
    public static User userExistByEmailAndPassword(User user){
        User resulutUser = staticUserDao.userExistByEmailAndPassword(user.getEmail(),user.getPassword());
        return resulutUser == null ? null : resulutUser;
    }


    /**
     * 添加聊天信息
     */
    public static void initChat(String id, int state, int msgType, String fromUserId, String toUserId){
        staticUserDao.initChat(id,state,msgType,fromUserId,toUserId);
    }

    /**
     * 获取朋友列表
     */
    public static List<Map<String,Object>> getFriends(String loginId){
        return staticUserDao.getFriends(loginId);
    }

    /**
     * 获取聊天
     */
    public static int getChat(String fromUserId, String toUserId){
        return staticUserDao.getChat(fromUserId,toUserId);
    }

    /**
     * 获取聊天
     */
    public static List<Map<String, String>> getChatList(String fromUserId){
        return staticUserDao.getChatList(fromUserId);
    }

    /**
     * 获取聊天
     */
    public static int addUserNameByEmail(String userName,String email){
         return staticUserDao.addUserNameByEmail(userName,email);
    }
}
