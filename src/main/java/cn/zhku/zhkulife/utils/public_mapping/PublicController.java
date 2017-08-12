package cn.zhku.zhkulife.utils.public_mapping;

import cn.zhku.zhkulife.modules.user.service.UserService;
import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.utils.Beans.Message;
import cn.zhku.zhkulife.utils.yiBanUtils.YiBanAuth;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/2 17:46.
 * @E-mail gonefuture@qq.com
 */
@Controller
@SessionAttributes("user")
public class PublicController {
    @Autowired
    UserService userService;

    @RequestMapping("errors")
    public String errors() {
        return "errors";
    }

    @RequestMapping("/yiban")
    public String yiban(Model model, HttpServletRequest request, HttpServletResponse response ,HttpSession httpSession) throws Exception {
        YiBanAuth yiBanAuth = new YiBanAuth();
        //得到user.me()的json对象
        JSONObject userMe = yiBanAuth.getUserMe(request,response);
        User user = (User) httpSession.getAttribute("user");

        System.out.println(user);
        System.out.println(userMe);

        User userUpdate = new User();

        if (userMe == null && !userMe.getString("status").equals("success")&& user == null) {
            model.addAttribute("msg","易班授权失败");
            return "errors";
        }
        else {
            userUpdate.setUserYibanid(userMe.getString("info"));
            userUpdate.setUserId(user.getUserId());
            userService.update(userUpdate);
            return "login";
        }
    }
}
