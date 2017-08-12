package cn.zhku.zhkulife.po.entity;

import java.util.Date;

public class Repair {
    private String repairId;

    private Date operateTime;

    private Date repairTime;

    private Integer repairState;

    private String userId;

    private String adminId;

    private Integer repairFeedback;

    private Integer zone;

    private String repairPic;

    private Integer userPhone;

    private Integer adminPhone;

    private String repairDetial;

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

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(Integer adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getRepairDetial() {
        return repairDetial;
    }

    public void setRepairDetial(String repairDetial) {
        this.repairDetial = repairDetial == null ? null : repairDetial.trim();
    }
}