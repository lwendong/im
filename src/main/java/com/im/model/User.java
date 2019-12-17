package com.im.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户id
     */
    private String id;
    /**
     * 登录id
     */
    private String loginId;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 个性签名
     */
    private String signaTure;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 简介
     */
    private String intro;
    /**
     * 头像地址
     */
    private String headPortrait;
    /**
     * 生肖
     */
    private String shengXiao;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 血型
     */
    private String bloodType;
    /**
     * 毕业院校
     */
    private String schoolTag;
    /**
     * 职业
     */
    private String vocation;
    /**
     * 国家ID
     */
    private String nationId;
    /**
     * 省份ID
     */
    private String provinceId;
    /**
     * 城市ID
     */
    private String cityId;
    /**
     * 好友策略ID
     */
    private String friendshipPolicyId;
    /**
     * 用户状态
     */
    private String userState;
    /**
     *  好友策略问题
     */
    private String friendPolicyQuestion;
    /**
     * 还有策略答案
     */
    private String friendPolicyAnswer;
    /**
     * 好友策略密码
     */
    private String friendPolicyPassword;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 拼音首字母
     */
    private String pySzm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignaTure() {
        return signaTure;
    }

    public void setSignaTure(String signaTure) {
        this.signaTure = signaTure;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getShengXiao() {
        return shengXiao;
    }

    public void setShengXiao(String shengXiao) {
        this.shengXiao = shengXiao;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSchoolTag() {
        return schoolTag;
    }

    public void setSchoolTag(String schoolTag) {
        this.schoolTag = schoolTag;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getFriendshipPolicyId() {
        return friendshipPolicyId;
    }

    public void setFriendshipPolicyId(String friendshipPolicyId) {
        this.friendshipPolicyId = friendshipPolicyId;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getFriendPolicyQuestion() {
        return friendPolicyQuestion;
    }

    public void setFriendPolicyQuestion(String friendPolicyQuestion) {
        this.friendPolicyQuestion = friendPolicyQuestion;
    }

    public String getFriendPolicyAnswer() {
        return friendPolicyAnswer;
    }

    public void setFriendPolicyAnswer(String friendPolicyAnswer) {
        this.friendPolicyAnswer = friendPolicyAnswer;
    }

    public String getFriendPolicyPassword() {
        return friendPolicyPassword;
    }

    public void setFriendPolicyPassword(String friendPolicyPassword) {
        this.friendPolicyPassword = friendPolicyPassword;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPySzm() {
        return pySzm;
    }

    public void setPySzm(String pySzm) {
        this.pySzm = pySzm;
    }
}
