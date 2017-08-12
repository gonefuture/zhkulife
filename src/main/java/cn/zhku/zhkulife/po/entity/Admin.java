package cn.zhku.zhkulife.po.entity;

public class Admin {
    private String adminId;

    private String adminPassword;

    private String adminRole;

    private Integer adminPhone;

    private Integer adminZone;

    private String adminName;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole == null ? null : adminRole.trim();
    }

    public Integer getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(Integer adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Integer getAdminZone() {
        return adminZone;
    }

    public void setAdminZone(Integer adminZone) {
        this.adminZone = adminZone;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }
}