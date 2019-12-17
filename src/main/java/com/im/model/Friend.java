package com.im.model;

public class Friend {
    private String id;

    private String friendId;

    private String userId;

    private String name;

    private Integer friendType;

    private String pySzm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFriendType() {
        return friendType;
    }

    public void setFriendType(Integer friendType) {
        this.friendType = friendType;
    }

    public String getPySzm() {
        return pySzm;
    }

    public void setPySzm(String pySzm) {
        this.pySzm = pySzm;
    }
}
