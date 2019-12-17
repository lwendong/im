package com.im.dao;

import com.im.model.Friend;
import com.im.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {

    @Select("SELECT * FROM im.user")
    @Results(id = "userResult", value = {
            @Result(column = "C_ID", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "C_LoginID", property = "loginId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_LoginName", property = "loginName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_NickName", property = "nickName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_PassWord", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_SignaTure", property = "signaTure", jdbcType = JdbcType.VARCHAR),
            @Result(column = "N_Sex", property = "sex", jdbcType = JdbcType.INTEGER),
            @Result(column = "D_Birthday", property = "birthday", jdbcType = JdbcType.DATE),
            @Result(column = "C_Telephone", property = "telephone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_Name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_Email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_Intro", property = "intro", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_HeadPortrait", property = "headPortrait", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_ShengXiao", property = "shengXiao", jdbcType = JdbcType.VARCHAR),
            @Result(column = "N_Age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "C_Constellation", property = "constellation", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_BloodType", property = "bloodType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_SchoolTag", property = "schoolTag", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_Vocation", property = "vocation", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_NationID", property = "nationId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_ProvincelID", property = "provinceId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_CityID", property = "cityId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_FriendshipPolicyID", property = "friendshipPolicyId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_UserState", property = "userState", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_FriendPolicyQuestion", property = "friendPolicyQuestion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_FriendPolicyAnswer", property = "friendPolicyAnswer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_FriendPolicyPassword", property = "friendPolicyPassword", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DT_createDate", property = "createDate", jdbcType = JdbcType.VARCHAR)
    })
    List<User> getAllUesr();

    @Select("SELECT C_ID,C_LoginID,C_LoginName,C_NickName,C_PassWord,C_SignaTure,N_Sex,D_Birthday,C_Telephone,C_Name,C_Email,C_Intro,C_HeadPortrait,C_ShengXiao,N_Age,C_Constellation,C_BloodType,C_SchoolTag,C_Vocation,C_NationID,C_ProvinceID,C_CityID,C_FriendshipPolicyID,C_UserState,C_FriendPolicyQuestion,C_FriendPolicyAnswer,C_FriendPolicyPassword,DT_createDate FROM im.user WHERE C_LoginName = #{loginName}")
    @ResultMap("userResult")
    User getUserByName(String loginName);

    @Select("SELECT C_ID,C_LoginID,C_LoginName,C_NickName,C_PassWord,C_SignaTure,N_Sex,D_Birthday,C_Telephone,C_Name,C_Email,C_Intro,C_HeadPortrait,C_ShengXiao,N_Age,C_Constellation,C_BloodType,C_SchoolTag,C_Vocation,C_NationID,C_ProvinceID,C_CityID,C_FriendshipPolicyID,C_UserState,C_FriendPolicyQuestion,C_FriendPolicyAnswer,C_FriendPolicyPassword,DT_createDate FROM im.user WHERE C_Email = #{email}")
    @ResultMap("userResult")
    User getUserByEmail(String email);

    @Select("SELECT COUNT(1) FROM im.user WHERE C_LoginName = #{loginName}")
    int userExistByLoginName(String loginName);

    @Select("SELECT COUNT(1) FROM im.user WHERE C_Email = #{email}")
    int userExistByEmail(String email);

    @Select("SELECT C_LoginID,C_LoginName,C_PassWord FROM im.user WHERE C_LoginName = #{loginName} AND C_PassWord = HEX(AES_ENCRYPT(#{password},'user_password'))")
    @ResultMap("userResult")
    User userExistByLoginNameAndPassword(String loginName,String password);

    @Select("SELECT C_LoginID,C_LoginName,C_PassWord FROM im.user WHERE C_Email = #{email} AND C_PassWord = HEX(AES_ENCRYPT(#{password},'user_password'))")
    @ResultMap("userResult")
    User userExistByEmailAndPassword(String email,String password);

    @Insert("INSERT INTO user(C_ID,C_LoginID,C_Email,C_PassWord,DT_createDate) VALUES(#{id},#{loginId},#{email},HEX(AES_ENCRYPT(#{password},'user_password')),NOW())")
    void addUser(User user);

    @Select("SELECT f.C_FriendId,f.C_UserId,f.C_Name,f.C_PySzm, u.C_NickName, u.C_SignaTure FROM im.user u LEFT JOIN im.friends f ON f.C_FriendId = u.C_LoginID WHERE f.C_UserId = #{loginId} AND (f.C_FriendType <> 0  AND f.C_FriendType IS NOT NULL) ORDER BY f.C_PySzm ")
    @Results(id = "friendResult",value = {
            @Result(column = "C_ID", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "C_FriendId", property = "friendId", jdbcType = JdbcType.CHAR),
            @Result(column = "C_UserId", property = "userId", jdbcType = JdbcType.CHAR),
            @Result(column = "C_Name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "C_FriendType", property = "friendType", jdbcType = JdbcType.INTEGER),
            @Result(column = "C_PySzm", property = "pySzm", jdbcType = JdbcType.VARCHAR),
    })
    List<Map<String,Object>> getFriends(String loginId);

    @Insert("INSERT INTO messages(C_ID,N_State,DT_Time,N_MessagesType,C_FromUserID,N_ToUserID) VALUES(#{id},#{state},NOW(),#{msgType},#{fromUserId},#{toUserId})")
    void initChat(String id, int state,int msgType,String fromUserId,String toUserId);

    @Select("SELECT COUNT(1) FROM im.messages WHERE C_FromUserID = #{fromUserId} AND N_ToUserID = #{toUserId} AND N_State = 0 AND N_MessagesType = 0 ")
    int getChat(String fromUserId,String toUserId);

    @Select("SELECT N_ToUserID,C_PostMessages FROM im.messages WHERE C_FromUserID = #{fromUserId} ")
    List<Map<String,String>> getChatList(String fromUserId);

    @Update("UPDATE user SET C_LoginName = #{userName} WHERE C_Email = #{email}")
    int addUserNameByEmail(String userName, String email);
}
