package cn.zhku.zhkulife.po.entity;

import java.io.Serializable;
import java.util.Date;

public class Water implements Serializable {
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

    private String adminPhone;

    private String userPhone;

    private static final long serialVersionUID = 1L;

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

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", waterId=").append(waterId);
        sb.append(", waterNum=").append(waterNum);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", waterTime=").append(waterTime);
        sb.append(", waterState=").append(waterState);
        sb.append(", userId=").append(userId);
        sb.append(", adminId=").append(adminId);
        sb.append(", waterFeedback=").append(waterFeedback);
        sb.append(", waterCount=").append(waterCount);
        sb.append(", zone=").append(zone);
        sb.append(", adminPhone=").append(adminPhone);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}