package cn.zhku.zhkulife.modules.water.controller;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.modules.water.service.WaterService;

import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.mapper.UserMapper;
import cn.zhku.zhkulife.utils.Beans.Message;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:46.
 * @E-mail gonefuture@qq.com
 */
@Controller
public class WaterController {
    @Autowired
    private WaterService waterService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminService adminService;


    @RequestMapping("water/list")
    @ResponseBody
    public  PageInfo<Water> list(String pageNum,String pageSize,Water water) throws Exception {
        if (pageNum == null)
            pageNum = "1";
        if (pageSize == null)
            pageSize = "10";
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        return new PageInfo<Water>(waterService.findAll(water));
    }



    @RequestMapping("user/waterList")
    @ResponseBody
    public  PageInfo<Water> userlist(HttpSession httpSession, String pageNum,String pageSize,Water water) throws Exception {
        if (pageNum == null)
            pageNum = "1";
        if (pageSize == null)
            pageSize = "10";
        User user = (User) httpSession.getAttribute("user");
        water.setUserId(user.getUserId());
        PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        return new PageInfo<Water>(waterService.getList(water));
    }


    @RequestMapping("user/bookWater")
    @ResponseBody
    public Message bookWater(HttpSession httpSession,Water water) throws Exception {
       User user = (User) httpSession.getAttribute("user");
        water.setUserPhone(user.getUserPhone());
        water.setUserId(user.getUserId());
        water.setWaterId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        water.setWaterState(1);
        water.setWaterTime(new Date());
        water.setZone(user.getUserZone());
        if (waterService.isHasBook(water)){
            return new Message("2","你之前有一个订单未完成");
        }
        else if(waterService.add(water) != 1 || water.getUserId() == null || water.getWaterNum()==null )
            return new Message("2","订水失败,请确认订水信息");
        else {
            return new Message("1","订水成功");
        }
    }

    /**
     * 普通用户完成订水订单
     * @param waterId   水的订单号
     * @return
     * @throws Exception
     */
    @RequestMapping("user/finishWater")
    @ResponseBody
    public Message finishUserWater(String waterId) throws Exception {
        Water water = new Water();
        water.setWaterId(waterId); water.setWaterState(4);
        if (waterService.update(water) != 1)
            return new Message("2","订单未完成");
        else
            return new Message("1","确认成功");
    }


    @RequestMapping("water/takeWater")
    @ResponseBody
    public Message takeWater(Water water) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getSession().getAttribute("admin");
        System.out.println(admin);
        water.setWaterId(water.getWaterId()); water.setAdminId(admin.getAdminId()); water.setWaterState(2);
        water.setAdminPhone(admin.getAdminPhone());
        if (waterService.update(water) != 1)
            return new Message("2","接单失败，请核实订单数据");
        else
            return new Message("1","接单成功，请尽快配送");
    }

    @RequestMapping("water/delivery")
    @ResponseBody
    public Message deliveryWater(String waterId) throws Exception {
        Water water = new Water();
        water.setWaterId(waterId); water.setWaterState(3);
        if (waterService.update(water) != 1)
            return new Message("2","订单未完成");
        else
            return new Message("1","确认配送成功");
    }



    @RequestMapping("user/feedbackWater")
    @ResponseBody
    public Message feedbackWater(String id,String feedback) throws Exception {
        Water water = new Water();
        water.setWaterId(id); water.setWaterFeedback(Integer.valueOf(feedback));
        if (waterService.update(water) != 1)
            return new Message("2","评价失败，请检查参数");
        else
            return new Message("1","评价成功");

    }

    @RequestMapping("water/updatePassword")
    @ResponseBody

    public Message updatePassword(String password) throws Exception {
        Admin admin = new Admin();
        Subject subject = SecurityUtils.getSubject();
        admin.setAdminId(subject.getPrincipal().toString());
        admin.setAdminPassword(password);
        if (adminService.update(admin) != 1)
            return new Message("2","修改密码失败，请检查输入");
        else
            return new Message("1","修改密码成功");
    }

    @RequestMapping("water/updatePhone")
    @ResponseBody
    public Message updatePhone(String phone) throws Exception {
        Admin admin = new Admin();

        Subject subject = SecurityUtils.getSubject();
        admin.setAdminId(subject.getPrincipal().toString());
        admin.setAdminPhone(phone);
        if (adminService.update(admin) != 1 )
            return new Message("2","修改手机号码失败，请检查输入");
        else {
            admin.setAdminPhone(phone);
            return new Message("1", "修改手机号码成功");
        }
    }

}
