package cn.zhku.zhkulife.po.entity;

import java.io.Serializable;
import java.util.Date;

public class Repair implements Serializable {
    private String repairId;

    private Date operateTime;

    private Date repairTime;

    private Integer repairState;

    private String userId;

    private String adminId;

    private Integer repairFeedback;

    private Integer zone;

    private String repairPic;

    private String userPhone;

    private String adminPhone;

    private String yibanInfo;

    private String repairDetial;

    private static final long serialVersionUID = 1L;

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId == null ? null : repairId.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public Integer getRepairState() {
        return repairState;
    }

    public void setRepairState(Integer repairState) {
        this.repairState = repairState;
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

    public Integer getRepairFeedback() {
        return repairFeedback;
    }

    public void setRepairFeedback(Integer repairFeedback) {
        this.repairFeedback = repairFeedback;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public String getRepairPic() {
        return repairPic;
    }

    public void setRepairPic(String repairPic) {
        this.repairPic = repairPic == null ? null : repairPic.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public String getYibanInfo() {
        return yibanInfo;
    }

    public void setYibanInfo(String yibanInfo) {
        this.yibanInfo = yibanInfo == null ? null : yibanInfo.trim();
    }

    public String getRepairDetial() {
        return repairDetial;
    }

    public void setRepairDetial(String repairDetial) {
        this.repairDetial = repairDetial == null ? null : repairDetial.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", repairId=").append(repairId);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", repairTime=").append(repairTime);
        sb.append(", repairState=").append(repairState);
        sb.append(", userId=").append(userId);
        sb.append(", adminId=").append(adminId);
        sb.append(", repairFeedback=").append(repairFeedback);
        sb.append(", zone=").append(zone);
        sb.append(", repairPic=").append(repairPic);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", adminPhone=").append(adminPhone);
        sb.append(", yibanInfo=").append(yibanInfo);
        sb.append(", repairDetial=").append(repairDetial);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}