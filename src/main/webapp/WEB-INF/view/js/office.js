/**
 * Created by Administrator on 2017/9/17 0017.
 */




/**
 * 加载被投诉的订水订单
 * @param datefrom 时间是从页面输入还是还是由系统选定今天往前推七天的
 *  @param pageNum 表示加载第几页
 * 参数值为1表示系统自拟,参数值为2表示从页面输入获取
 */
function waterOfComplain(datefrom,pageNum) {
    var end =new Date();
    var since=new Date(end.getTime()-24*60*60*7*1000);
    end=new String(end.getFullYear()+"-"+(end.getMonth()+1)+"-"+end.getDate());
    since=new String(since.getFullYear()+"-"+(since.getMonth()+1)+"-"+since.getDate());
    console.log(end+"     "+since);
    if(datefrom==2){
        since=$("#water-startDate").text();
        end=$("#water-endDate").text();
        console.log("since:"+since+"  end"+end);
        if(since==""||since==null){
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("请选择起始时间");
            /////调用函数,显示模态框
            alertInfo();
            return;
        }
        if(end==""||end==null){
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("请选择结束时间");
            /////调用函数,显示模态框
            alertInfo();
            return;
        }
    }
    $.ajax({
        type: "get",
        url: "../water/repined",
        dataType: "json",
        data: {'sinceTime': since,"endTime":end,"pageNum":pageNum},
        success : function(data) {
            $("#waterOrderList").empty();
            $("#waterOrderpageNav").empty();
            list = eval(data).list;
            total= eval(data).total;
            if(total==0){
                $("#waterOrderList").append("<br/><br/>还没有相关订单!");
            }else {
                ////投诉订单的数量
                $("#waterNum").empty();
                $("#waterNum").append(total);
                $("#waterOrderList").empty();
                for (var i in list) {
                    var waterId = list[i].waterId;
                    var waterNum = list[i].waterNum;
                    var waterState = list[i].waterState;
                    if(waterState==1){
                        waterState="暂无人接单";
                    }else if(waterState==2){
                        waterState="已被接单";
                    }else if(waterState==3){
                        waterState="正在配送中";
                    }else if(waterState==4){
                        waterState="订单已完成";
                    }
                    var adminPhone = list[i].adminPhone;
                    var userPhone = list[i].userPhone;
                    var zone = list[i].zone;
                    if(zone==1){
                        zone="海珠校区";
                    }else if(zone==2){
                        zone="白云校区";
                    }
                    var userId = list[i].userId;
                    var yibanInfo = list[i].yibanInfo;
                    ///waterTime为订单的下单的时间
                    var waterTime = list[i].waterTime;
                    waterTime=waterTime.substring(0,16);
                    var waterFeedback = list[i].waterFeedback;
                    if(waterFeedback==1){
                        waterFeedback="订单超过两天无人受理";
                    }else if(waterFeedback==2){
                        waterFeedback="已接单超过两天,但没有进行配送";
                    }else if(waterFeedback==3){
                        waterFeedback="水质有问题";
                    }else if(waterFeedback==4){
                        waterFeedback="工作人员态度差";
                    }
                    $("#waterOrderList").append("订单号:&nbsp&nbsp" + waterId + "<br/>校区:&nbsp&nbsp"+zone+"<br/>宿舍编号:&nbsp&nbsp"+userId
                        +"<br/>订单状态:&nbsp&nbsp"+waterState+"<br/>下单时间:&nbsp&nbsp" + waterTime+"<br/>数量:&nbsp&nbsp"+ waterNum +
                        "桶<br/>送水人员手机:&nbsp&nbsp"+ adminPhone+"<br/>宿舍联系电话:&nbsp&nbsp"+userPhone+"<br/>下单人:&nbsp&nbsp"+
                        yibanInfo+"<br/>投诉问题:&nbsp&nbsp"+waterFeedback+"<hr>"
                    );

                }
                showPage(eval(data).pages,eval(data).pageNum,4,total);
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}

/**
 * 加载被投诉的维修订单
 * @param datefrom 时间是从页面输入还是还是由系统选定今天往前推七天的
 * @param pageNum 表示加载第几页
 * 参数值为1表示系统自拟,参数值为2表示从页面输入获取
 */
function repairOfComplain(datefrom,pageNum) {
    var end =new Date();
    var since=new Date(end.getTime()-24*60*60*7*1000);
    end=new String(end.getFullYear()+"-"+(end.getMonth()+1)+"-"+end.getDate());
    since=new String(since.getFullYear()+"-"+(since.getMonth()+1)+"-"+since.getDate());
    console.log(end+"     "+since);
    if(datefrom==2){
        since=$("#repair-startDate").text();
        end=$("#repair-endDate").text();
        console.log("since:"+since+"  end"+end);
        if(since==""||since==null){
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("请选择起始时间");
            /////调用函数,显示模态框
            alertInfo();
            return;
        }
        if(end==""||end==null){
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("请选择结束时间");
            /////调用函数,显示模态框
            alertInfo();
            return;
        }
    }
    $.ajax({
        type: "get",
        url: "../repair/repined",
        dataType: "json",
        data: {'sinceTime': since,"endTime":end,"pageNum":pageNum},
        success : function(data) {
            $("#repairOrderList").empty();
            $("#repairOrderpageNav").empty();
            list = eval(data).list;
            total= eval(data).total;
            if(total==0){
                $("#repairOrderList").append("<br/><br/>还没有相关订单!");
            }else {
                /////投诉订单的数量
                $("#repairNum").empty();
                $("#repairNum").append(total);
                $("#repairOrderList").empty();
                for (var i in list) {
                    var repairId = list[i].repairId;
                    var repairTime = list[i].repairTime;
                    repairTime=repairTime.substring(0,16);
                    var repairState = list[i].repairState;
                    if(repairState==1){
                        repairState="暂无人接单";
                    }else if(repairState==2){
                        repairState="已被接单";
                    }else if(repairState==3){
                        repairState="正在配修中";
                    }else if(repairState==4){
                        repairState="订单已完成";
                    }
                    var userId = list[i].userId;
                    var userPhone = list[i].userPhone;
                    var adminPhone = list[i].adminPhone;
                    var repairState = list[i].repairState;
                    var repairFeedback = list[i].repairFeedback;
                    if(repairFeedback==1){
                        repairFeedback="订单超过两天无人受理";
                    }else if(repairFeedback==2){
                        repairFeedback="已接单超过两天,但没有进行维修";
                    }else if(repairFeedback==3){
                        repairFeedback="维修质量不好";
                    }else if(repairFeedback==4){
                        repairFeedback="工作人员态度差";
                    }
                    var zone = list[i].zone;
                    if(zone==1){
                        zone="海珠校区";
                    }else if(zone==2){
                        zone="白云校区";
                    }
                    var repairDetail=list[i].repairDetial;
                    var repairPic=list[i].repairPic;
                    repairPic=new String(repairPic);
                    var img;
                    if(repairPic=="null"||repairPic=="NULL"){
                        img="用户未上传";
                    }else {
                        repairPic="/zhkulife/img/repair/"+repairPic;
                        img="<br/><img src="+repairPic+" width='200' height='120'>";
                    }
                    var yibanInfo = list[i].yibanInfo;
                    $("#repairOrderList").append("订单号:&nbsp&nbsp" + repairId + "<br/>校区:&nbsp&nbsp"+zone+"<br/>宿舍编号:&nbsp&nbsp"+userId+
                        "<br/>下单时间:&nbsp&nbsp" + repairTime+ "<br/>维修人员手机:&nbsp&nbsp"+ adminPhone+"<br/>宿舍联系电话:&nbsp&nbsp"+userPhone+
                        "<br/>报修信息:&nbsp&nbsp"+repairDetail+"<br/>图片详情:&nbsp&nbsp"+img+"<br/>下单人:&nbsp&nbsp"+yibanInfo+
                        "<br/>投诉问题:&nbsp&nbsp"+repairFeedback+"<hr>"
                    );

                }
                showPage(eval(data).pages,eval(data).pageNum,5,total);
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}



/**
 *  用于订单显示时分页
 * @param pages 表示总页数
 * @param pageNum 表示当前页面
 * @param type表示订单类型,2表示为订水订单,3表示为维修订单,4表示为订水投诉订单,5表示为维修投诉订单
 * @param total表示记录总数
 */
function showPage(pages,pageNum,type,total){
    var pageNav;
    var functionName;
    if(type==2){
        pageNav="#waterOrderpageNav";
        functionName="searchWater";
    }else if(type==3){
        pageNav="#repairOrderpageNav";
        functionName="searchRepair"
    }else if(type==4){
        pageNav="#waterOrderpageNav";
        functionName="waterOfComplain";
    }else if(type==5){
        pageNav="#repairOrderpageNav";
        functionName="repairOfComplain";
    }
    var begin;
    var end;
    var next=pageNum+1;
    var pre=pageNum-1;
    if(pages<=10){
        begin=1;
        end=pages;
    }else{
        begin=pageNum-4;
        end=pageNum+5;
        if(begin<1){
            begin=1;
            end=10;
        }
        if(end>pages){
            begin=pages-9;
            end=pages;
        }
    }
    if(pageNum!=1){
        $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+pre+")"+"'>上一页</button>");
    }
    for(var i=begin;i<=end;++i){
        if(i===pageNum){
            $(pageNav).append("<button type='button' class='btn btn-success' ><b>"+i+"</b></button>");
        }else{
            $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+i+")"+"'>"+i+"</button>");
        }
    }
    if(pageNum!=pages){
        $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+next+")"+"'>下一页</button>");
    }
    $(pageNav).append("<br/>共"+total+"条记录");
}









/**
 *"模态框,显示提示信息
 */
function alertInfo() {
    $('#alert').modal();
}


/**
 * 用于添加学生用户
 */
function addStu(){
    var zone= $("select[name='zone']").val();
    var room= $("input[name='room']").val();
    room=room.toUpperCase();
    var password= $("input[name='password']").val();
    var phone= $("input[name='phone']").val();
    console.log("zone: "+zone+"room: "+room+"password:  "+password+"phone: "+phone);
    if(zone==""||room==""||password==""||phone==""){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请完整输入信息");
        /////调用函数,显示模态框
        alertInfo();
    }else{
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("信息正在提交,请勿关闭页面");
        /////调用函数,显示模态框
        alertInfo();
        $.ajax({
            type: "get",
            url: "../office/user/add",
            dataType: "json",
            data: {'userZone': zone,"userId":room,"userRoom":room,"userPassword":password,"userPhone":phone,"totalWater":0},
            success : function(data, textStatus) {
                var msg = eval(data).msg;
                console.log(msg);///查看后台返回的信息
                var info = eval(data).info;
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append(info);
            },
            error : function(xhr, status, errMsg) {
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("系统异常,请稍后再试!");
            }
        });
    }
}





/**
 * 用于添加工作人员
 */
function addWorker(){
    var zone= $("select[name='zone']").val();
    var role= $("select[name='role']").val();
    var name= $("input[name='name']").val();
    var password= $("input[name='password']").val();
    var phone= $("input[name='phone']").val();
    console.log("zone: "+zone+"role: "+role+"name:  "+name+"password:  "+password+"phone: "+phone);
    if(zone==""||role==""||name==""||password==""||phone==""){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请完整输入信息");
        /////调用函数,显示模态框
        alertInfo();
    }else{
        $.ajax({
            type: "get",
            url: "../admin/addRole",
            dataType: "json",
            data: {"adminId":phone,"adminPassword":password,"adminRole":role,"adminPhone":phone,'adminZone': zone,"adminName":name,},
            success : function(data, textStatus) {
                var msg = eval(data).msg;
                console.log(msg);///查看后台返回的信息
                var info = eval(data).info;
                console.log(info);
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append(info);
                /////调用函数,显示模态框
                alertInfo();
            },
            error : function(xhr, status, errMsg) {
                alert(errMsg);
            }
        });
    }
}





/**
 *通过用户输入的学生用户ID来查找某个单一用户
*/
function searchStu(){
    var userId= $("input[name='userId']").val();
    if(userId==""||userId=="null"||userId==undefined){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请输入学生的登录账号");
        /////调用函数,显示模态框
        console.log(userId);
        alertInfo();
    }else{
    console.log(userId);
    $.ajax({
        type: "get",
        url: "../admin/findUser",
        dataType: "json",
        data: {'userId': userId},
        success : function(data, textStatus) {
            $("#showResult").empty();
            var user= eval(data);
            if(user==0||user==""||user==null){
                $("#showResult").append("不存在符合条件的账号");
            }else{
                    var userId = user.userId;
                    var userPassword = user.userPassword;
                    var userRoom = user.userRoom;
                    var userZone = user.userZone;
                    if (userZone == 1) {
                        userZone = "海珠校区";
                    } else if (userZone == 2) {
                        userZone = "白云校区";
                    }
                    var userPhone = user.userPhone;
                    //var yibanInfo = list[i].yibanInfo;
                    var loginTime = user.loginTime;
                    var totalWater = user.totalWater;
                    $("#showResult").append("用户账号:&nbsp&nbsp"+userId+"<br/>用户密码:&nbsp&nbsp"+userPassword+
                        "<br/>校区:&nbsp&nbsp"+userZone+"<br/>房间编号:&nbsp&nbsp"+userRoom+"<br/>宿舍联系手机:&nbsp&nbsp"+userPhone+
                        "<br/>上次登录时间:&nbsp&nbsp"+loginTime+ "<br/>房间订水总数:&nbsp&nbsp"+totalWater+"桶<hr>" );

            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
    }
}





/**
 *显示当前管理人员管理权限范围的工作人员的信息
 * * @param pageNum 显示的当前的页码
 */
function showStaff(pageNum){
    $.ajax({
        type: "get",
        url: "../admin/findRole",
        dataType: "json",
        data: {'pageNum': pageNum},
        success : function(data, textStatus) {
            $("#showResult").empty();
            $("#showStaffNav").empty();
            var total= eval(data).total;
            var list = eval(data).list;
            if(total==0){
                $("#showResult").append("不存在符合条件的账号");
            }else{
                /////获取当前登录人员用cookie保存的ID
                var officeAdm=getCookie("officeAdm");
                for(var i in list) {
                    var adminId = list[i].adminId;
                    var adminPassword = list[i].adminPassword;
                    var adminRole = list[i].adminRole;
                    var job;
                    if(adminRole==2){
                        job="送水工人";
                    }else if(adminRole==3){
                        job="维修工人";
                    }else if(adminRole==4){
                        job="饮用水公司负责人";
                    }else if(adminRole==5){
                        job="维修公司负责人";
                    }else if(adminRole==6){
                        job="学校领导";
                    }
                    var adminPhone = list[i].adminPhone;
                    var adminZone = list[i].adminZone;
                    if (adminZone == 1) {
                        adminZone = "海珠校区";
                    } else if (adminZone == 2) {
                        adminZone = "白云校区";
                    }
                    var adminName=list[i].adminName;
                    if(adminName==""){
                        adminName="无";
                    }
                    ////只显示不属于学校领导的账号
                    if(adminRole!=6){
                        //////只显示当前工作人员的直接下属
                            $("#showResult").append("用户账号:&nbsp&nbsp"+"<span id='"+adminId+"'>"+adminId+"</span>"+"<br/>姓&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp名:&nbsp&nbsp"+adminName+
                             "<br/>用户密码:&nbsp&nbsp"+adminPassword+ "<br/>工作类型:&nbsp&nbsp"+job+"<br/>工作校区:&nbsp&nbsp"+
                             adminZone+"<br/>联系号码:&nbsp&nbsp"+adminPhone+"<hr/>");
                    }
                }
                showPageOfStaff(eval(data).pages,eval(data).pageNum,total-1);
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}



/**
pages:表示总页数
pageNum:表示当前页数
total:表示总共查询到的记录总数
**/
function showPageOfStaff(pages,pageNum,total){
    var pageNav;
    var functionName;
    pageNav="#showStaffNav";/////在html页面中将页码显示栏放到该Id的元素里
    functionName="showStaff";/////用户点击页码或者下一页时将要触发的函数名
    var begin;
    var end;
    var next=pageNum+1;
    var pre=pageNum-1;
    if(pages<=10){
        begin=1;
        end=pages;
    }else{
        begin=pageNum-4;
        end=pageNum+5;
        if(begin<1){
            begin=1;
            end=10;
        }
        if(end>pages){
            begin=pages-9;
            end=pages;
        }
    }
    if(pageNum!=1){
        $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+pre+")"+"'>上一页</button>");
    }
    for(var i=begin;i<=end;++i){
        if(i===pageNum){
            $(pageNav).append("<button type='button' class='btn btn-success' ><b>"+i+"</b></button>");
        }else{
            $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+i+")"+"'>"+i+"</button>");
        }
    }
    if(pageNum!=pages){
        $(pageNav).append("<button type='button' class='btn btn-info' onclick='"+functionName+"("+next+")"+"'>下一页</button>");
    }
    $(pageNav).append("<br/>共"+total+"条记录");
}











/**
 * 查找组合条件查询订水订单
 * @param pageNum  要查询第几页
 *  <<<<<<<<<<<<<<<该方法存在不足的地方是查询的的时间时根据operate_time来进行查找的>>>>>>>>>>>>>>>>>>
 */
function searchWater(pageNum) {
    since = $("#my-startDate").text();
    end = $("#my-endDate").text();
    console.log(since+end);
    if (since == "" || end == "") {
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请选择时间范围");
        /////调用函数,显示模态框
        alertInfo();
    } else {
        var url= "../water/list?since="+since+"&end="+end+"&pageNum="+pageNum;
        var userId= $("input[name='userId']").val();
        userId=new String(userId);
        var adminId= $("input[name='adminId']").val();
        adminId=new String(adminId);
        if(userId!=""){
            url=url+"&userId="+userId;
        }
        if(adminId!=""){
            url=url+"&adminId="+adminId;
        }
        $.ajax({
            type: "get",
            url: url,
            dataType: "json",
            success: function (data, textStatus) {
                $("#showResult").empty();
                $("#waterOrderpageNav").empty();
                total = eval(data).total;
                list = eval(data).list;
                console.log(list);
                if (total == 0) {
                    $("#showResult").append("没有查到符合条件的订单");
                } else {
                    for(var i in list) {
                        var waterId = list[i].waterId;
                        var waterNum = list[i].waterNum;
                        var waterState = list[i].waterState;
                        if (waterState == 1) {
                            waterState = "暂无人接单";
                        } else if (waterState == 2) {
                            waterState = "已被接单";
                        } else if (waterState == 3) {
                            waterState = "正在配送中";
                        } else if (waterState == 4) {
                            waterState = "订单已完成";
                        }
                        var adminPhone = list[i].adminPhone;
                        adminPhone = new String(adminPhone);
                        if (adminPhone == "null" || adminPhone == "") {
                            adminPhone = "暂无";
                        }
                        var userPhone = list[i].userPhone;
                        var zone = list[i].zone;
                        if (zone == 1) {
                            zone = "海珠校区";
                        } else if (zone == 2) {
                            zone = "白云校区";
                        }
                        var userId = list[i].userId;
                        var yibanInfo = list[i].yibanInfo;
                        ///waterTime为订单的下单的时间
                        var waterTime = list[i].waterTime;
                        waterTime = waterTime.substring(0, 16);
                        var waterFeedback = list[i].waterFeedback;
                        if (waterFeedback == 1) {
                            waterFeedback = "订单超过两天无人受理";
                        } else if (waterFeedback == 2) {
                            waterFeedback = "已接单超过两天,但没有进行配送";
                        } else if (waterFeedback == 3) {
                            waterFeedback = "水质有问题";
                        } else if (waterFeedback == 4) {
                            waterFeedback = "工作人员态度差";
                        } else if (waterFeedback == 5) {
                            waterFeedback = "好评";
                        } else {
                            waterFeedback = "暂无反馈";
                        }
                        $("#showResult").append("订单号:&nbsp&nbsp" + waterId + "<br/>校区:&nbsp&nbsp" + zone + "<br/>宿舍编号:&nbsp&nbsp" + userId
                            + "<br/>订单状态:&nbsp&nbsp" + waterState + "<br/>下单时间:&nbsp&nbsp" + waterTime + "<br/>数量:&nbsp&nbsp" + waterNum +
                            "桶<br/>送水人员手机:&nbsp&nbsp" + adminPhone + "<br/>宿舍联系电话:&nbsp&nbsp" + userPhone + "<br/>下单人:&nbsp&nbsp" +
                            yibanInfo + "<br/>投诉问题:&nbsp&nbsp" + waterFeedback + "<hr>");
                    }
                    showPage(eval(data).pages,eval(data).pageNum,2,total);
                }
            },
            error: function (xhr, status, errMsg) {
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("系统异常,请稍后再试");
                /////调用函数,显示模态框
                alertInfo();
            }
        });
    }
}


/**
 *查找组合条件查询维修订单
 *
 **@param pageNum  要查询第几页
 * <<<<<<<<<<<<<<<<<<<<<<<<<该方法存在不足的地方是查询的的时间时根据operate_time来进行查找的>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 */
function searchRepair(pageNum) {
    since = $("#my-startDate").text();
    end = $("#my-endDate").text();
    console.log(since+end);
    if (since == "" || end == "") {
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("请选择时间范围");
        /////调用函数,显示模态框
        alertInfo();
    } else {
        var url = "../repair/list?since=" + since + "&end=" + end+"&pageNum="+pageNum;
        var userId = $("input[name='userId']").val();
        userId = new String(userId);
        var adminId = $("input[name='adminId']").val();
        adminId = new String(adminId);
        if (userId != "") {
            url = url + "&userId=" + userId;
        }
        if (adminId != "") {
            url = url + "&adminId=" + adminId;
        }
        $.ajax({
            type: "get",
            url: url,
            dataType: "json",
            success: function (data, textStatus) {
                console.log(data);
                $("#showResult").empty();
                $("#repairOrderpageNav").empty();
                total = eval(data).total;
                list = eval(data).list;
                if (total == 0) {
                    $("#showResult").append("没有查到符合条件的订单");
                } else {
                    for(var i in list) {
                        var repairId = list[i].repairId;
                        var repairTime = list[i].repairTime;
                        repairTime=repairTime.substring(0,16);
                        var repairState = list[i].repairState;
                        if(repairState==1){
                            repairState="暂无人接单";
                        }else if(repairState==2){
                            repairState="已被接单";
                        }else if(repairState==3){
                            repairState="正在配修中";
                        }else if(repairState==4){
                            repairState="订单已完成";
                        }
                        var userId = list[i].userId;
                        var userPhone = list[i].userPhone;
                        var adminPhone = list[i].adminPhone;
                        adminPhone=new String(adminPhone);
                        if(adminPhone=="null"){
                            adminPhone="暂无";
                        }
                        var repairState = list[i].repairState;
                        var repairFeedback = list[i].repairFeedback;
                        if(repairFeedback==1){
                            repairFeedback="订单超过两天无人受理";
                        }else if(repairFeedback==2){
                            repairFeedback="已接单超过两天,但没有进行维修";
                        }else if(repairFeedback==3){
                            repairFeedback="维修质量不好";
                        }else if(repairFeedback==4){
                            repairFeedback="工作人员态度差";
                        }else if(repairFeedback==5){
                            repairFeedback="好评";
                        }else {
                            repairFeedback="无";
                        }
                        var zone = list[i].zone;
                        if(zone==1){
                            zone="海珠校区";
                        }else if(zone==2){
                            zone="白云校区";
                        }
                        var repairDetail=list[i].repairDetial;
                        var repairPic=list[i].repairPic;
                        repairPic=new String(repairPic);
                        var img;
                        if(repairPic=="null"||repairPic=="NULL"){
                            img="用户未上传";
                        }else {
                            repairPic="/zhkulife/img/repair/"+repairPic;
                            img="<br/><img src="+repairPic+" width='200' height='120'>";
                        }
                        var yibanInfo = list[i].yibanInfo;
                        $("#showResult").append("订单号:&nbsp&nbsp" + repairId + "<br/>校区:&nbsp&nbsp"+zone+"<br/>宿舍编号:&nbsp&nbsp"+userId+
                            "<br/>下单时间:&nbsp&nbsp" + repairTime+ "<br/>维修人员手机:&nbsp&nbsp"+ adminPhone+"<br/>宿舍联系电话:&nbsp&nbsp"+userPhone+
                            "<br/>报修信息:&nbsp&nbsp"+repairDetail+"<br/>图片详情:&nbsp&nbsp"+img+"<br/>下单人:&nbsp&nbsp"+yibanInfo+
                            "<br/>投诉问题:&nbsp&nbsp"+repairFeedback+"<hr>");
                    }
                    showPage(eval(data).pages,eval(data).pageNum,3,total);
                }
            },
            error: function (xhr, status, errMsg) {
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("系统异常,请稍后再试");
                /////调用函数,显示模态框
                alertInfo();
            }
        });
    }
}




/**
 * 根据cookie名获取cookie的值
 * @param cname cookie名
 * @returns {*} cookie值
 */
function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}





///修改用户手机
function modifyPhone(){
    var phone= $("input[name='phone']").val();
    $.ajax({
        type: "get",
        url: "../admin/updatePhone",
        dataType: "json",
        data: {'adminPhone': phone},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==1){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改成功!");
                /////调用函数,显示模态框
                alertInfo();
            }else if(msg==2){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改手机失败!请稍后再试!");
                /////调用函数,显示模态框
                alertInfo();
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}



///修改密码
function updatePassword(){
    var password= $("input[name='password']").val();
    if(password=="123456"||password=="654321"||password=="asdfgh"){
        ////清空提示模态框里面的内容
        $("#alert-info").empty();
        ////向模态框添加服务器返回的信息
        $("#alert-info").append("密码过于简单,请重新输入");
        /////调用函数,显示模态框
        alertInfo();
        return;
    }
    $.ajax({
        type: "get",
        url: "../admin/updatePassword",
        dataType: "json",
        data: {'adminPassword': password},
        success : function(data, textStatus) {
            var msg = eval(data).msg;
            if(msg==1){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改成功!");
                /////调用函数,显示模态框
                alertInfo();
            }else if(msg==2){
                ////清空提示模态框里面的内容
                $("#alert-info").empty();
                ////向模态框添加服务器返回的信息
                $("#alert-info").append("修改密码失败!请稍后再试!");
                /////调用函数,显示模态框
                alertInfo();
            }
        },
        error : function(xhr, status, errMsg) {
            ////清空提示模态框里面的内容
            $("#alert-info").empty();
            ////向模态框添加服务器返回的信息
            $("#alert-info").append("系统异常,请稍后再试!");
            /////调用函数,显示模态框
            alertInfo();
        }
    });
}


/**
 * 获取url中的参数值
 * @param name
 * @returns {null}
 */
function getUrlParam(name){
    //构造一个含有目标参数的正则表达式对象
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    //匹配目标参数
    var r = window.location.search.substr(1).match(reg);
    //返回参数值
    if (r!=null) {
        return unescape(r[2]);
    }
    return null;
}
/**
 * ################################################按钮触发区###################################
 */


/**
 * ################################################按钮触发区###################################
 */

