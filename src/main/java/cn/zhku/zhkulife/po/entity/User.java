package cn.zhku.zhkulife.po.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String userId;

    private String userPassword;

    private String userRoom;

    private String userPhone;

    private Integer userZone;

    private String userYibanid;

    private Integer userFlag;

    private Date loginTime;

    private Integer totalWater;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom == null ? null : userRoom.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getUserZone() {
        return userZone;
    }

    public void setUserZone(Integer userZone) {
        this.userZone = userZone;
    }

    public String getUserYibanid() {
        return userYibanid;
    }

    public void setUserYibanid(String userYibanid) {
        this.userYibanid = userYibanid == null ? null : userYibanid.trim();
    }

    public Integer getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Integer userFlag) {
        this.userFlag = userFlag;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(Integer totalWater) {
        this.totalWater = totalWater;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userRoom=").append(userRoom);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userZone=").append(userZone);
        sb.append(", userYibanid=").append(userYibanid);
        sb.append(", userFlag=").append(userFlag);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", totalWater=").append(totalWater);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}