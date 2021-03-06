package cn.zhku.zhkulife.utils.Beans;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:25.
 * @E-mail gonefuture@qq.com
 */
public class Message {
   //状态码
    private String msg;
    //信息
    private String info;
    //用来放各种关键的属性，如userId
    private String key;

    private Integer  zone;

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Message() {
    }

    public Message(String msg,String info) {
        this.msg = msg;
        this.info = info;
    }

    public Message(String msg,String info,String key) {
        this.msg = msg;
        this.info = info;
        this.key = key;
    }

    public Message(String msg, String info, String key, Integer zone) {
        this.msg = msg;
        this.info = info;
        this.key = key;
        this.zone = zone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", info='" + info + '\'' +
                ", key='" + key + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
