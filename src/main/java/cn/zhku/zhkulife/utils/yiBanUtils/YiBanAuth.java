package cn.zhku.zhkulife.utils.yiBanUtils;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import cn.zhku.zhkulife.utils.Beans.UserMe;
import cn.zhku.zhkulife.utils.Beans.YiBanUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/8 10:30.
 * @E-mail gonefuture@qq.com
 */
public class YiBanAuth {
    private static  String appid   = "3002f750eae1c39f";
    private static  String seckey  = "036fdae6b34db42336dc280360561106";
    private static  String backurl = "http://139.159.240.59:8081/zhkulife/auth";


    public  static UserMe getUserMe(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String code = request.getParameter("code");
        System.out.println(code);
        Authorize au = new Authorize(appid, seckey);

        if (code == null || code.isEmpty()) {
            String url = au.forwardurl(backurl, "test", Authorize.DISPLAY_TAG_T.WEB);
            //response.sendRedirect(url);
            return null;
        }
        else {
            String text = au.querytoken(code, backurl);
            JSONObject token = JSONObject.parseObject(text);
            System.out.println(token);

            User user = new User(token.getString("access_token"));
            JSONObject userRealMe = JSONObject.parseObject(user.me());
            System.out.println("-----------------------------"+userRealMe+"----------"+userRealMe.toString());
            JSONObject userJson = userRealMe.getJSONObject("info");
            return (UserMe) JSON.toJavaObject(userJson,UserMe.class);
        }

    }

    public  static YiBanUser verifyMe(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String code = request.getParameter("code");

        Authorize au = new Authorize(appid, seckey);

        if (code == null || code.isEmpty()) {
            String url = au.forwardurl(backurl, "test", Authorize.DISPLAY_TAG_T.WEB);
            response.sendRedirect(url);
            return null;
        }
        else {
            String text = au.querytoken(code, backurl);
            JSONObject token = JSONObject.parseObject(text);

            YiBanUserUtil yiBanUserUtil = new YiBanUserUtil(token.getString("access_token"));
            JSONObject userJson = JSONObject.parseObject(yiBanUserUtil.verifyMe()).getJSONObject("info");

            return  (YiBanUser)JSON.toJavaObject(userJson,YiBanUser.class);

        }

    }
}
