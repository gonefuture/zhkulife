package cn.zhku.zhkulife.modules.water.controller;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.modules.user.service.UserService;
import cn.zhku.zhkulife.modules.water.service.WaterService;

import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.User;
import cn.zhku.zhkulife.po.entity.Water;
import cn.zhku.zhkulife.po.mapper.UserMapper;
import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.Beans.Message;
import cn.zhku.zhkulife.utils.Beans.UserMe;
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

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    /**     管理员查找订单多条件查询
     *
     * @param commonQo  参数：pageNum页码，pageSize 记录数量 ，since 开始时间  end 结束时间
     * @param water 除时间类外的Water类属性
     * @return
     * @throws Exception
     */
    @RequestMapping("water/list")
    @ResponseBody
    public  PageInfo<Water> list(CommonQo commonQo , Water water) throws Exception {
        System.out.println(commonQo);
        PageHelper.startPage(commonQo.getPageNum(),commonQo.getPageSize(),"water_time desc");
        return new PageInfo<Water>(waterService.findAll(commonQo,water));
    }



    @RequestMapping("user/waterList")
    @ResponseBody
    public  PageInfo<Water> userlist(HttpSession httpSession, CommonQo commonQo,Water water) throws Exception {
        User user = (User) httpSession.getAttribute("user");
        water.setUserId(user.getUserId());
        PageHelper.startPage(commonQo.getPageNum(),commonQo.getPageSize(),"water_time desc");
        return new PageInfo<Water>(waterService.getList(water));
    }

    /**         学生订水
     *
     * @param httpSession 会话
     * @param water   必须参数：water
     * @return
     * @throws Exception
     */
    @RequestMapping("user/bookWater")
    @ResponseBody
    public Message bookWater(HttpSession httpSession,Water water) throws Exception {
       User sessionUser = (User) httpSession.getAttribute("user");
       User DBUser =userService.get(sessionUser.getUserId());
        if ("0".equals(DBUser.getUserPhone())) {    //在订水前获取获取其手机号
           return new Message("2","你的手机号未设置");
        }else if ("123456".equals(DBUser.getUserPassword())){
            return new Message("2","你的密码过于简单，不能为123456，前立刻更改密码");
        }
        water.setYibanInfo(httpSession.getAttribute("yibanInfo").toString());
        water.setUserPhone(sessionUser.getUserPhone());
        water.setUserId(sessionUser.getUserId());
        water.setWaterId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        water.setWaterState(1);
        water.setWaterTime(new Date());
        water.setZone(sessionUser.getUserZone());
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

    /**
     *  送水人员接单
     * @param waterId 水订单号
     * @return
     * @throws Exception
     */
    @RequestMapping("water/takeWater")
    @ResponseBody
    public Message takeWater(String waterId) throws Exception {
        Water water = new Water();
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getSession().getAttribute("admin");
        water.setWaterId(waterId); water.setAdminId(admin.getAdminId()); water.setWaterState(2);
        water.setAdminPhone(admin.getAdminPhone());
        if (waterService.get(waterId).getWaterState()!= 1 || waterService.update(water) != 1) // 若订单状态不为1，则立刻返回错误码。
            return new Message("2","接单失败，你的订单可能已经被别人借了");
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
