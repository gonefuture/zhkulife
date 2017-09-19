package cn.zhku.zhkulife.modules.repair.controller;

import cn.zhku.zhkulife.modules.admin.service.AdminService;
import cn.zhku.zhkulife.modules.repair.service.RepairService;
import cn.zhku.zhkulife.modules.user.service.UserService;
import cn.zhku.zhkulife.po.entity.Admin;
import cn.zhku.zhkulife.po.entity.Repair;

import cn.zhku.zhkulife.po.entity.User;

import cn.zhku.zhkulife.utils.Beans.CommonQo;
import cn.zhku.zhkulife.utils.Beans.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @author 钱伟健 gonefutre
 * @date 2017/8/1 22:48.
 * @E-mail gonefuture@qq.com
 */

@Controller
public class RepairController  {
    @Autowired
    private RepairService repairService;

    @Autowired
    private AdminService adminService;

    @Autowired
    UserService userService;

    /**
     *      维修订单多条件查询
     * @param commonQo  参数： pageNum 页码,pageSize 单页记录数 ，since 开始时间， end 结束时间
     * @param repair 除时间参数外的参数
     * @return
     * @throws Exception
     */
    @RequestMapping("repair/list")
    @ResponseBody
    public PageInfo<Repair>list(CommonQo commonQo ,Repair repair) throws Exception {

        PageHelper.startPage(commonQo.getPageNum(),commonQo.getPageSize(),"repair_time desc");  //按照下单时间倒序排序
        return new PageInfo<Repair>(repairService.findAll(commonQo, repair));
    }

    /**        学生用户查找维修订单，只能查找自身的订单，通过订单状态码查询
     *
     * @param commonQo 参数： pageNum 页码,pageSize 单页记录数 ，since 开始时间， end 结束时间
     * @param repair       参数：repairState 订单状态码
     * @param httpSession
     * @return
     * @throws Exception
     */
    @RequestMapping("user/repairList")
    @ResponseBody
    public PageInfo<Repair>repairList( CommonQo commonQo , Repair repair,HttpSession httpSession) throws Exception {
        User user = (User) httpSession.getAttribute("user");
        repair.setUserId(user.getUserId());
        PageHelper.startPage(commonQo.getPageNum(),commonQo.getPageSize(),"repair_time desc");

        return new PageInfo<Repair>(repairService.getList(repair));
    }

    /**         学生报修
     *
     * @param httpSession  缓存
     * @param request 请求
     * @param repair 参数：图片 Pic ， 维修时间详情 repair_detail
     * @param Pic
     * @return
     * @throws Exception
     */
    @RequestMapping("user/bookRepair")
    @ResponseBody
    public Message bookRepair(HttpSession httpSession , HttpServletRequest request,Repair repair, MultipartFile Pic) throws Exception {
        User sessionUser = (User) httpSession.getAttribute("user");
        User DBUser =userService.get(sessionUser.getUserId());
        if ("0".equals(DBUser.getUserPhone())  && "0".equals(DBUser.getUserPhone())) {    //在订水前获取获取其手机号
            return new Message("3","你的手机号未设置并且密码过于简单不能为123456 ");
        }else if ("123456".equals(DBUser.getUserPassword())){
            return new Message("2","你的密码过于简单，不能为123456，前立刻更改密码 ");
        }else if("0".equals(DBUser.getUserPhone())){
            return new Message("2","你的手机号未设置 ");
        }
        repair.setYibanInfo(httpSession.getAttribute("yibanInfo").toString());
        repair.setUserId(sessionUser.getUserId());
        repair.setRepairId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        repair.setRepairTime(new Date());
        repair.setRepairState(1);
        repair.setZone(sessionUser.getUserZone());
        if(Pic !=null) {
            //储存图片的物理路径
            String realPath = request.getServletContext().getRealPath("/WEB-INF/view/img/repair/");

            String originalFileName = Pic.getOriginalFilename();
            //新的的图片名称
            String newFileName = UUID.randomUUID().toString().replace("-","").toUpperCase()+originalFileName.substring(originalFileName.lastIndexOf("."));
            //新图片文件
            File newFile = new java.io.File(realPath+newFileName);
            //将内存中的数据写入磁盘
            Pic.transferTo(newFile);
            //将新图片名称写到repair中
            repair.setRepairPic(newFileName);
        } else {

            if ( repairService.add(repair) !=1 )
                return new Message("2","报修失败,信息填写不正确");
            else
                return new Message("1","没有上传图片但报修成功");

        }
        if ( repairService.add(repair) !=1 )
            return new Message("2","报修失败,信息填写不正确");
        else
            return new Message("1","报修成功");
    }

    /**
     *  维修人员接单
     * @param repairId 维修订单号
     * @return
     * @throws Exception
     */
    @RequestMapping("repair/takeRepair")
    @ResponseBody
    public Message takeRepair(String repairId) throws Exception {
        Repair repair = new Repair();  // 直接接受一个参数可以防止恶意篡改订单
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getSession().getAttribute("admin");
        repair.setRepairId(repairId); repair.setRepairState(2); repair.setAdminPhone(admin.getAdminPhone());
        repair.setAdminId(admin.getAdminId());
        if (repairService.get(repairId).getRepairState() != 1 || repairService.update(repair) != 1) // 若订单状态不为1，则立刻返回错误码。
            return new Message("2","接单失败，请核实订单数据");
        else
            return new Message("1","接单成功，请尽快维修");
    }


    /**
     *      维修人员完成维修订单
     * @param repairId  维修订单编号
     * @return
     * @throws Exception
     */
    @RequestMapping("repair/delivery")
    @ResponseBody
    public Message deliveryRepair(String repairId) throws Exception {
        Repair repair = new Repair();
        repair.setRepairId(repairId); repair.setRepairState(3);   //实体类填充两个参数， repairId, repairState
        if(repairService.update(repair) != 1)
            return new Message("2","报修未完成,信息填写不正确");
        else
            return new Message("1","报修完成");
    }


    /**
     *      学生完成维修订单
     * @param repairId  维修订单编号
     * @return
     * @throws Exception
     */
    @RequestMapping("user/finishRepair")
    @ResponseBody
    public Message finishRepair(String repairId) throws Exception {
        Repair repair = new Repair();
        repair.setRepairId(repairId); repair.setRepairState(4);   //实体类填充两个参数， repairId, repairState
        if(repairService.update(repair) != 1)
            return new Message("2","报修未完成,信息填写不正确");
        else
            return new Message("1","报修完成");
    }

    @RequestMapping("user/feedbackRepair")
    @ResponseBody
    public Message feedbackRepair(String id, String feedback) throws Exception {
        Repair repair = new Repair();
        repair.setRepairId(id); repair.setRepairFeedback(Integer.valueOf(feedback));
        if(repairService.update(repair) != 1)
            return new Message("2","评价失败，请检验参数");
        else
            return new Message("1","评价完成");
    }

    @RequestMapping("repair/updatePassword")
    @ResponseBody
    public Message updatePassword(String password) throws Exception {
        Admin admin = new Admin();
        Subject subject = SecurityUtils.getSubject();
        admin.setAdminId(subject.getPrincipal().toString());
        admin.setAdminPassword(password);
        if (adminService.update(admin) != 1)
            return new Message("2","修改密码失败，请检查参数");
        else
        return new Message("1","修改密码成功");
    }

    @RequestMapping("repair/updatePhone")
    @ResponseBody
    public Message updatePhone(String phone) throws Exception {
        Admin admin = new Admin();
        Subject subject = SecurityUtils.getSubject();
        admin.setAdminId(subject.getPrincipal().toString());
        admin.setAdminPhone(phone);
        if (adminService.update(admin) != 1 )
            return new Message("2","修改手机号码失败");
        else {
            admin.setAdminPhone(phone);
            return new Message("1", "修改手机号码成功");
        }
    }

    @RequestMapping("repair/repined")
    @ResponseBody
    public PageInfo<Repair> waterRepined(CommonQo commonQo) {
        PageHelper.startPage(commonQo.getPageNum(),commonQo.getPageSize(),"repair_time desc");
        return new PageInfo<Repair>(repairService.repined(commonQo));
    }


}
