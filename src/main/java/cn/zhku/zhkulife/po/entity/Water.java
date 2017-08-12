package cn.zhku.zhkulife.po.entity;

import java.util.Date;

public class Water {
    private String waterId;

    private Integer waterNum;

    private Date operateTime;

    private Date waterTime;

    private Integer waterState;

    private String userId;

    private String adminId;

    private Integer waterFeedback;

    private Integer waterCount;

    private Integer zone;

    private Integer adminPhone;

    private Integer userPhone;

    public String getWaterId() {
        return waterId;
    }

    public void setWaterId(String waterId) {
        this.waterId = waterId == null ? null : waterId.trim();
    }

    public Integer getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(Integer waterNum) {
        this.waterNum = waterNum;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Date getWaterTime() {
        return waterTime;
    }

    public void setWaterTime(Date waterTime) {
        this.waterTime = waterTime;
    }

    public Integer getWaterState() {
        return waterState;
    }

    public void setWaterState(Integer waterState) {
        this.waterState = waterState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public Integer getWaterFeedback() {
        return waterFeedback;
    }

    public void setWaterFeedback(Integer waterFeedback) {
        this.waterFeedback = waterFeedback;
    }

    public Integer getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(Integer waterCount) {
        this.waterCount = waterCount;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(Integer adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }
}