package cn.zhku.zhkulife.utils.Beans;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/9/16 11:18.
 * @E-mail gonefuture@qq.com
 *
 *  说明：用于捕获易班的认证信息
 */
public class UserMe {
    String yb_userid;
    String yb_username;
    String yb_sex;

    public String getYb_userid() {
        return yb_userid;
    }

    public void setYb_userid(String yb_userid) {
        this.yb_userid = yb_userid;
    }

    public String getYb_username() {
        return yb_username;
    }

    public void setYb_username(String yb_username) {
        this.yb_username = yb_username;
    }

    public String getYb_sex() {
        return yb_sex;
    }

    public void setYb_sex(String yb_sex) {
        this.yb_sex = yb_sex;
    }

    @Override
    public String toString() {
        return "{" +
                "yb_userid:" + yb_userid +
                ", yb_username:" + yb_username +
                ", yb_sex:" + yb_sex  +
                "}";
    }
}
