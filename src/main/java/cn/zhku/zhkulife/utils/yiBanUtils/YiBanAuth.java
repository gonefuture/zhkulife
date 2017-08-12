package cn.zhku.zhkulife.utils.yiBanUtils;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
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
    private static  String appid   = "300cc66769689ae2";
    private static  String seckey  = "34831d10902a19fd6476c01b7bd48bc3";
    private static  String backurl = "http://localhost:8080/zhkulife/yiban";


    public JSONObject getUserMe(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

            User user = new User(token.getString("access_token"));
            JSONObject userJson = JSONObject.parseObject(user.me());
            return userJson;
        }

    }
}
