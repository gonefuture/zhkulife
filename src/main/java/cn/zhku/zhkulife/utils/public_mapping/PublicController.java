package cn.zhku.zhkulife.utils.public_mapping;

import cn.zhku.zhkulife.modules.user.service.UserService;
import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.utils.Beans.Message;
import cn.zhku.zhkulife.utils.Beans.UserMe;
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

    @RequestMapping("index")
    public String index() {
        return "index";
    }


    /**
     * 易班认证，授权后，将学生信息放入session
     * @param model
     * @param request
     * @param response
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("/auth")
    public String yiban(Model model, HttpServletRequest request, HttpServletResponse response ,HttpSession httpSession) throws Exception {
        //得到user.me()的json对象
        UserMe userMe = YiBanAuth.getUserMe(request,response);
        System.out.println("-------------------------------------这是易班账号信息-------------------------------");
        System.out.println(userMe);

        if (userMe == null) {
            model.addAttribute("msg","易班授权失败,请检查你的账号或联系开发人员");
            return "errors";
        }
        else {
            httpSession.setAttribute("yibanInfo",userMe);
           // userService.update(userUpdate);
            return "redirect:userlog.html";
        }
    }
}
